//
// Created by yzzCool on 2021/4/13.
//

#ifndef YZZCOOL_TRIANGLESAMPLE_H
#define YZZCOOL_TRIANGLESAMPLE_H


#include <GLES3/gl3.h>

class TriangleSample
{
public:
    TriangleSample();
    virtual ~TriangleSample();

    GLuint m_VertexShader;
    GLuint m_FragmentShader;
    GLuint m_ProgramObj;

    virtual void Init();

    virtual void Draw();

    virtual void Destroy();
};


#endif //YZZCOOL_TRIANGLESAMPLE_H
