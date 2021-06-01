package utils;

import entities.UtilizadoresEntity;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }

    public static String getLoggedName() {
        HttpSession session = SessionUtils.getSession();
        UtilizadoresEntity utilizadoresEntity = (UtilizadoresEntity) session.getAttribute("user_in_session");
        return utilizadoresEntity.getNome();
    }

    public static String getLoggedID() {
        HttpSession session = SessionUtils.getSession();
        UtilizadoresEntity utilizadoresEntity = (UtilizadoresEntity) session.getAttribute("user_in_session");
        return utilizadoresEntity.getId() + "";
    }

    public static String getLoggedType() {
        HttpSession session = SessionUtils.getSession();
        UtilizadoresEntity utilizadoresEntity = (UtilizadoresEntity) session.getAttribute("user_in_session");
        return utilizadoresEntity.getTipo();
    }
}