Language Structure Plan for FOOP
================================

Objectives
----------
To write a language which implements the functional paradigm in OOP with little boilerplate.

Built-in Datatypes
------------------
Int  
Str  
Bool  
Collection
Func

Primitives won't really be a thing. Everything will be a class.

Variable Assignment
-------------------
```
var <DataType> variable is <assignment>;
val <DataType> constant is <assigmnet>;
```

Control Flow
------------
```
if(cond) {

}
else if {

}
else {

}
```

Class Layout
------------
```
class <Name> {
    extends (<Class> [, <Class]...])
    implements <interface>
    
    expects {
        <scope> <DataType> <variable>;
        local Int constructorOnlyInt;
        attribute Double classAttr;
    } 
    attributes {
        
    }
    
    get attr [{ codeblock }]
    set attr [{ codeblock }]
    
    meth <method name> (param1, param2..): <DataType> {
        <code block>
    }
}
```