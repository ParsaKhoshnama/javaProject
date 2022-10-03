package workWithFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InaccessibleObjectException;

public class MyObjectOutPutStream extends ObjectOutputStream
{
    private static File file=null;
    private static FileOutputStream fileOutputStream=null;
    public static void setFile(File FILE)
    {
        file=FILE;
    }
    public static File getFile()
    {
        return file;
    }
    public MyObjectOutPutStream(File file)throws IOException
    {
        super(new FileOutputStream(file,true));
    }
    public void writeStreamHeader()throws IOException
    {
        if(file==null)
            super.writeStreamHeader();
        else
        {
            if(file.length()==0)
                super.writeStreamHeader();
            else
                this.reset();
        }
    }
}
