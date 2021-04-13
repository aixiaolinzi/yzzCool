//
// Created by yzzCool on 2021/4/13.
//

//
// Created by ByteFlow on 2019/7/9.
//

#include <cstdlib>
#include "TriangleSample.h"
#include "../util/LogUtil.h"


GLint	m_ProgramObj;

TriangleSample::TriangleSample()
{

}

TriangleSample::~TriangleSample()
{
    if (m_ProgramObj)
    {
        glDeleteProgram(m_ProgramObj);
    }

}



GLuint LoadShaderGL ( GLenum type, const char *shaderSrc )
{
    GLuint shader;
    GLint compiled;

    // Create the shader object
    shader = glCreateShader ( type );

    if ( shader == 0 )
    {
        return 0;
    }

    // Load the shader source
    glShaderSource ( shader, 1, &shaderSrc, NULL );

    // Compile the shader
    glCompileShader ( shader );

    // Check the compile status
    glGetShaderiv ( shader, GL_COMPILE_STATUS, &compiled );

    if ( !compiled )
    {
        GLint infoLen = 0;

        glGetShaderiv ( shader, GL_INFO_LOG_LENGTH, &infoLen );

        if ( infoLen > 1 )
        {
            char *infoLog = (char *)malloc ( sizeof ( char ) * infoLen );

            glGetShaderInfoLog ( shader, infoLen, NULL, infoLog );
            LOGCATE("Error compiling shader:[%s]", infoLog );

            free ( infoLog );
        }

        glDeleteShader ( shader );
        return 0;
    }

    return shader;

}



void TriangleSample::Init()
{
    char vShaderStr[] =
            "#version 300 es                          \n"
            "layout(location = 0) in vec4 vPosition;  \n"
            "void main()                              \n"
            "{                                        \n"
            "   gl_Position = vPosition;              \n"
            "}                                        \n";

    char fShaderStr[] =
            "#version 300 es                              \n"
            "precision mediump float;                     \n"
            "out vec4 fragColor;                          \n"
            "void main()                                  \n"
            "{                                            \n"
            "   fragColor = vec4 ( 1.0, 0.0, 0.0, 1.0 );  \n"
            "}                                            \n";

//    m_ProgramObj = GLUtils::CreateProgram(vShaderStr, fShaderStr, m_VertexShader, m_FragmentShader);


    GLuint vertexShader;
    GLuint fragmentShader;
    GLuint programObject;
    GLint linked;

    // Load the vertex/fragment shaders
    vertexShader = LoadShaderGL ( GL_VERTEX_SHADER, vShaderStr );
    fragmentShader = LoadShaderGL ( GL_FRAGMENT_SHADER, fShaderStr );
//    vertexShader = LoadShader ( GL_VERTEX_SHADER, pVertexShader );
//    fragmentShader = LoadShader ( GL_FRAGMENT_SHADER, pFragmentShader );

    // Create the program object
    programObject = glCreateProgram ( );

    if ( programObject == 0 )
    {
        return;
    }

    glAttachShader ( programObject, vertexShader );
    glAttachShader ( programObject, fragmentShader );

    // Link the program
    glLinkProgram ( programObject );

    // Check the link status
    glGetProgramiv ( programObject, GL_LINK_STATUS, &linked );

    if ( !linked )
    {
        GLint infoLen = 0;

        glGetProgramiv ( programObject, GL_INFO_LOG_LENGTH, &infoLen );

        if ( infoLen > 1 )
        {
            char *infoLog = (char *)malloc ( sizeof ( char ) * infoLen );

            glGetProgramInfoLog ( programObject, infoLen, NULL, infoLog );
            LOGCATE("Error linking program:[%s]", infoLog );

            free ( infoLog );
        }

        glDeleteProgram ( programObject );
        return;
    }

    // Store the program object
    m_ProgramObj = programObject;

    glClearColor ( 1.0f, 1.0f, 0.0f, 0.0f );


}

void TriangleSample::Draw()
{
    LOGCATE("TriangleSample::Draw");
    GLfloat vVertices[] = {
            0.0f,  0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
    };

    if(m_ProgramObj == 0)
        return;

    // Use the program object
    glUseProgram (m_ProgramObj);

    // Load the vertex data
    glVertexAttribPointer (0, 3, GL_FLOAT, GL_FALSE, 0, vVertices );
    glEnableVertexAttribArray (0);

    glDrawArrays (GL_TRIANGLES, 0, 3);

}

void TriangleSample::Destroy() {}
