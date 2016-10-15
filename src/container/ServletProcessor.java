package container;
import java.io.File;  
import java.io.IOException;
import java.net.URL;  
import java.net.URLClassLoader;  
import javax.servlet.Servlet;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
  
public class ServletProcessor
{  
    public void process(Request request, Response response)  
    {  
    	//得到路径，并取得相应servlet名称
        String uri = request.getUri();  
        String servletname = uri.substring(uri.lastIndexOf("/") + 1);  
        URLClassLoader loader = null;
        try  
        {  
            //创建路径及相应数据流
            URL[] urls = new URL[1];  
            File classPath = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator);
            //将完整路径转为相应格式
            URL url = classPath.toURI().toURL();
            urls[0]=url;
            //加载该路径
            loader = new URLClassLoader(urls);  
        }  
        catch (IOException e)  
        {  
            System.out.println(e.toString());  
        }  
        Class<?> myClass = null; 
        try  
        {  
            //加载相应路径下的相应类  
            myClass = loader.loadClass(readXml.getPath(servletname));  
        }  
        catch (ClassNotFoundException e)  
        {  
            System.out.println(e.toString());  
        }  
        Servlet servlet = null;  
        try  
        {  
            //加载相应的类并实例化，然后调用servlet的init,service方法。  
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        }  
        catch (Exception e)  
        {  
            System.out.println(e.toString());  
        }  
        catch (Throwable e)  
        {  
            System.out.println(e.toString());  
        }  
    }  
}  