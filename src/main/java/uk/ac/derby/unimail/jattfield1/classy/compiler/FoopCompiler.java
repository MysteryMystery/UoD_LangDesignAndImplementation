package uk.ac.derby.unimail.jattfield1.classy.compiler;


import java.io.*;

public class FoopCompiler {
    private final String outPath = "./out/foop/";
    private final String binExt = ".foop.ser";

    public final Scope globalScope = new Scope();
    private Scope previousScope = null;
    private Scope currentScope = globalScope;

    public Scope getCurrentScope() {
        return currentScope;
    }

    public Scope getPreviousScope() {
        return previousScope;
    }

    public void compileInstruction(Instruction instruction){
        this.currentScope.compileInstruction(instruction);
    }

    public void setCurrentScope(Scope currentScope) {
        this.previousScope = this.currentScope;
        this.currentScope = currentScope;
    }

    public Scope getGlobalScope() {
        return globalScope;
    }

    public void compileTo(String fileName){
        try {
            (new File(outPath)).mkdirs();
            fileName = outPath + fileName + binExt;

            File file = new File(fileName);
            if (file.exists()){
                file.delete();
                file = new File(fileName);
            }
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(globalScope);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(String fileName){
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
            Scope scope = (Scope) objectInputStream.readObject();
            objectInputStream.close();

            scope.executeInstructions();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public String getBinExt() {
        return binExt;
    }

    public String getOutPath() {
        return outPath;
    }
}
