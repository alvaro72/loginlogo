package io.jenkins.plugins.loginlogo;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hudson.init.InitMilestone;
import hudson.model.User;
import jenkins.model.Jenkins;

/**
 * This filter intercept to images in Jenkins Login Page and return a
 * forward response to the local image resource.
 *
 * @author alvaro72
 **/
public class LoginLogoFilter implements Filter {
    private static final String pluginName = "login-logo";
    private static final String pluginStr = "/plugin/login-logo/";
    private static final Logger logger = Logger.getLogger("io.jenkins.plugins.loginlogo");

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) req;
            final HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            final String uri = httpServletRequest.getRequestURI();
            final String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

            if (path.startsWith(pluginStr) && ( uri.endsWith(".jpg") || uri.endsWith(".png") ) ) {
                imageToResponse(httpServletRequest, httpServletResponse);

                return;
            }
        }
        chain.doFilter(req, resp);
    }

    private void imageToResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final ServletContext cntx= req.getServletContext();
        final String path = req.getRequestURI().substring(req.getContextPath().length());
        final String filePiece = path.substring(pluginStr.length());

        // Get the absolute path of the image
        String filename = Jenkins.get().getPlugin(pluginName).getWrapper().baseResourceURL.getFile()
         + filePiece;
        // retrieve mimeType dynamically
        String mime = cntx.getMimeType(filename);
        if (mime == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        resp.setContentType(mime);
        File file = new File(filename);
        if(!file.exists()) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        logger.log(Level.INFO, ".jpg or .png");
        resp.setContentLength((int)file.length());

        FileInputStream in = new FileInputStream(file);
        OutputStream out = resp.getOutputStream();

        byte[] buf = new byte[1024];
        int count;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
    }

    @Override
    public void destroy() {
    }
}
