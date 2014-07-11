/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package svilproject2014.gui;

import javax.swing.JOptionPane;

/**
 *
 * @author mirko
 */
public class DialogMessage {
    public static void popupTesto(String txt){
        JOptionPane.showMessageDialog(null,txt);
    }
    public static Integer popupYesNo(String txt,String titolo){
        return JOptionPane.showConfirmDialog(null,
            txt,titolo,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
    }
    public static String popupString(String txt,String titolo){
        return (String) JOptionPane.showInputDialog(null,txt,titolo,
            JOptionPane.INFORMATION_MESSAGE);
    }
    public static String popupChoice(String txt,String titolo,String[]opt){
        if(opt!=null && opt.length>0)
            return (String)JOptionPane.showInputDialog(null,
                txt,titolo,JOptionPane.INFORMATION_MESSAGE, null,opt, opt[0]);
        return null;
     }
}
