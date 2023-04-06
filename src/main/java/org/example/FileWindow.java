package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileWindow extends JFrame{
    private JPanel panel1;
    private JTextField filePathField;
    private JButton buttonCreate;
    private JButton buttonDelete;
    private JButton buttonRename;
    private JButton buttonRead;
    private JButton buttonWrite;
    private JTextArea fileText;

    public FileWindow(){
        setContentPane(panel1);
        this.pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = filePathField.getText().trim();
                if(path.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Введите путь к файлу!","Ошибка",JOptionPane.ERROR_MESSAGE);
                }else{
                    File newFile = new File(path);
                    try{
                        newFile.createNewFile();
                        JOptionPane.showMessageDialog(null,"Файл создан!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null,"Файл не создан!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = filePathField.getText().trim();
                if(path.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Введите путь к файлу!","Ошибка",JOptionPane.ERROR_MESSAGE);
                }else{
                    File newFile = new File(path);
                    if(newFile.exists()){
                        newFile.delete();
                        JOptionPane.showMessageDialog(null,"Файл удален!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null,"Файл не найден!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonRename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = filePathField.getText().trim();
                if(path.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Введите путь к файлу!","Ошибка",JOptionPane.ERROR_MESSAGE);
                }else{
                    File newFile = new File(path);
                    if(newFile.exists()){
                        String newName = JOptionPane.showInputDialog("Введите новой имя: ");
                        if(newName == null){newName = "";}
                        if(!newName.trim().isEmpty()){
                            File renameFile = new File(newFile.getParent()+"\\"+newName);
                            newFile.renameTo(renameFile);
                            JOptionPane.showMessageDialog(null,"Файл переименован!","Сообщение",JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(null,"Введено пустое имя", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    } else{
                        JOptionPane.showMessageDialog(null,"Файл не найден!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonWrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = filePathField.getText().trim();
                if(path.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Введите путь к файлу!","Ошибка",JOptionPane.ERROR_MESSAGE);
                }else{
                    File newFile = new File(path);
                    if(newFile.exists()){
                       try{
                           OutputStream os = new FileOutputStream(newFile);
                           BufferedOutputStream bos = new BufferedOutputStream(os);
                           bos.write(fileText.getText().getBytes());
                           bos.close();
                           os.close();
                           JOptionPane.showMessageDialog(null,"Запись выполнена", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                       }catch(Exception ex){
                           JOptionPane.showMessageDialog(null,"Не удалось записать в файл!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                           ex.printStackTrace();
                       }
                    }else{
                        JOptionPane.showMessageDialog(null,"Файл не найден!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = filePathField.getText().trim();
                if(path.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Введите путь к файлу!","Ошибка",JOptionPane.ERROR_MESSAGE);
                }else{
                    File newFile = new File(path);
                    if(newFile.exists()){
                        try{
                            InputStream is = new FileInputStream(newFile);
                            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
                            String text = "";
                            while(bReader.ready()){
                                text += bReader.readLine() + "\n";
                            }
                            fileText.setText(text);
                            bReader.close();
                            is.close();
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null,"Не удалось прочитать из файла!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Файл не найден!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
