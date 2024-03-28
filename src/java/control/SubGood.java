/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import entity.Good;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Admin
 */
public class SubGood extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                // lay ra id cua good muon them so luong
        String idGood = request.getParameter("id");
        int gid = Integer.parseInt(idGood);
        // tim good co idGood trong gio hang va tang so luong
        HttpSession session = request.getSession();
        CopyOnWriteArrayList<Good> cart = (CopyOnWriteArrayList<Good>) session.getAttribute("List");
        for (Good g : cart) {
            if (g.getId() == gid) {
                g.setAmount(g.getAmount() - 1);
            }
        }
        for (Good g : cart) {
            // xoa di mon hang co so luong = 0 trong cart
            if (g.getAmount() == 0) {
                cart.remove(g);
            }
        }

        double total = 0;
        for (Good g : cart) {
            total += g.getPrice() * g.getAmount();
        }
        double vat = (total) * 0.15;
        double sum = total + vat;
        request.setAttribute("total", total);
        request.setAttribute("vat", vat);
        request.setAttribute("sum", sum);

        String url = "Cart.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
