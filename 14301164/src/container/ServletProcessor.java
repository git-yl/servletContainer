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
    	//�õ�·������ȡ����Ӧservlet����
        String uri = request.getUri();  
        String servletname = uri.substring(uri.lastIndexOf("/") + 1);  
        URLClassLoader loader = null;
        try  
        {  
            //����·������Ӧ������
            URL[] urls = new URL[1];  
            File classPath = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator);
            //������·��תΪ��Ӧ��ʽ
            URL url = classPath.toURI().toURL();
            urls[0]=url;
            //���ظ�·��
            loader = new URLClassLoader(urls);  
        }  
        catch (IOException e)  
        {  
            System.out.println(e.toString());  
        }  
        Class<?> myClass = null; 
        try  
        {  
            //������Ӧ·���µ���Ӧ��  
            myClass = loader.loadClass(readXml.getPath(servletname));  
        }  
        catch (ClassNotFoundException e)  
        {  
            System.out.println(e.toString());  
        }  
        Servlet servlet = null;  
        try  
        {  
            //������Ӧ���ಢʵ������Ȼ�����servlet��init,service������  
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