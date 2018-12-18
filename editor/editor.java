import java.io.*;
import java.util.*;

class direactory
{
    String dirname;
     String file_Name;
     boolean check = false ;
     String name2;
     Scanner input = new Scanner(System.in);
     // deal with the stuff related to directory 
    void dir() throws IOException
    {
        //Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of dir : ");
        String dir = input.nextLine();
        File file = new File(dir);
        boolean check_dir  = file.isDirectory();
        if(check_dir)
        {
            System.out.println("Directory exist in system");
            dirname = dir;

        }
        else
        {
            System.out.println("There is no such directory ");
            System.out.println("Do you want to make Directory ? [yes] [no] : ");
            String choice;
            choice = input.nextLine();
            if(choice.equalsIgnoreCase("yes"))
            {
                System.out.println("Enter the name of Directory: ");
                dir = input.nextLine();
                file = new File(dir);
                file.mkdir();
                dirname = dir;
            }
            else
            {
               System.out.println("Enter the path where you want to save file: ");
               dir = input.nextLine();
               file = new File(dir);
               dirname = dir;
            }
        }
    }
}
class file extends direactory
{
    //This function show the list of files and directory 
    void list() throws IOException
    {
        File file;
        File file2;
        String choice;
        String[] file_List;
        int len;
        System.out.println("Do you want to list of file and  direactory: [yes] [no]");
        choice = input.nextLine();
       System.out.println();
        if(choice.equalsIgnoreCase("yes"))
        {
            if(check == true)
            {
              
                file2 = new File(dirname + "/"  + name2);
                file_List = file2.list();
                
            }
            else{
                file = new File(dirname);
                file_List = file.list();
                
            }
           
            if(file_List == null){
                System.out.println("There is no file");

            }
            else{
                if(check == true)
                {
                    for(String file_list2 : file_List)
                    {
                        file2  = new File(file_list2);
                        System.out.println(file_list2);
                    }
                }
                else{
                    for(String file_list2 : file_List)
                    {
                        file  = new File(file_list2);
                        System.out.println(file_list2);
                    }
                }
                
            }
            
            System.out.println();
            open();
        }
        if(choice.equalsIgnoreCase("no")){
            open();
        }
       
    }
    // This function help to open the file 
    void open() throws IOException{
      //  Scanner input = new Scanner(System.in);
        System.out.println("Enter the filename  with extension : ");
        String name  = input.nextLine();
        File file = new File(dirname,name);
        if(file.isFile())
        {
            System.out.println("File exist");
            file_Name = name;
        }
        else if(file.isDirectory())
        {
            change Change= new change();
            Change.change_dir();
            
        }
        else
        {
            System.out.println("file dont exist");
            System.out.println("Do you want to make new file ? [yes] [no]");
            String choice = input.nextLine();
            if(choice.equalsIgnoreCase("yes"))
            {
                System.out.println("Enter file name : ");
                name = input.nextLine();
                
                 file = new File(dirname,name);
                file.createNewFile();
                file_Name = name;
            }
            if(choice.equalsIgnoreCase("no"))
            {
                list();
            }
        }
    }
}

class change extends file{
    // this change the directory
    void change_dir() throws IOException
    {
        System.out.println("This is not file it is directory");
            System.out.println("Do you want to open that : [yes] [no]");
         //   Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.equalsIgnoreCase("yes"))
            {
                check = true;
                name2 = file_Name;
                File file2 = new File(dirname + "/"  + name2);
                System.out.println("Enter the filename  with extension : ");
                String  name  = input.nextLine();
                 file_Name = name;
                 file2 = new File(dirname + "/" + name2 , file_Name);
                 if(file2.isFile())
                 {
                     System.out.println("File exist");
                     file_Name = name;
                 }
                 else if(file2.isDirectory())
                 {
                     change_dir();
                     
                 }
                 else
                 {
                     System.out.println("file dont exist");
                     System.out.println("Do you want to make new file ? [yes] [no]");
                     choice = input.nextLine();
                     if(choice.equalsIgnoreCase("yes"))
                     {
                         System.out.println("Enter file name : ");
                         name = input.nextLine();
                         
                          file2= new File(dirname,name);
                         file2.createNewFile();
                         file_Name = name;
                     }
                     if(choice.equalsIgnoreCase("no"))
                     {
                         list();
                     }
                 }

                 
                 
            }
            if(choice.equalsIgnoreCase("no"))
            {
                open();
            }
    }
}
class read_file extends change
{
    // it read the file 
    void read() throws IOException
    {   
        long length ;
        BufferedReader br;
        if(check == true)
        {
            File file2 = new File(dirname + "/" + name2 , file_Name);
             length = file2.length();
             br = new BufferedReader(new FileReader(file2));
        }
        else{
            File file = new File(dirname,file_Name);
            length = file.length();
            br = new BufferedReader(new FileReader(file));
        }
            
           
            boolean data = false;
            String read;
            
            while(length >=0){
                read = br.readLine();
                if(read == null)
                {
                    if(data == true)
                    {
                        break;
                    }
                    System.out.println("There is no data in file");
               
                    break;
                }
                else
                {
                    System.out.println(read);
                    data = true;
                }

            }
            br.close();
        }
}

class file_write extends read_file
{
    
    //it help to write in the file
    void write() throws IOException
    {
        boolean overwrite;
        BufferedWriter bw;
            System.out.println("Do you want to override the data in the file: [yes] [no] ");
          //  Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.equalsIgnoreCase("yes"))
            {
               overwrite = true;
            }
            else
            {
                overwrite = false;
            }
            if(check == true)
             {
                File file2 = new File(dirname + "/" + name2 , file_Name);
                bw = new BufferedWriter(new FileWriter(file2,overwrite));
            }
            else
            {
                File file = new File(dirname,file_Name);
                 bw = new BufferedWriter(new FileWriter(file,overwrite));
            }
            String data = "not";
            System.out.println("[\"save\"] to save the data to file");
            System.out.println("Enter the data : ");
            while(data !="save")
            {
                data = input.nextLine();
               if(data.equalsIgnoreCase("save"))
                {
                  
                    break;
                }
                bw.write(data);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
}
class delete_file extends file_write
{
	// This delete the file from system
    void del()
    {
        boolean value;
        if(check == true)
        {
            File file2 = new File(dirname + "/" + name2 , file_Name);
            file2.deleteOnExit();
            value = file2.delete();
            if(value)
            {
                System.out.println();
                System.out.println("file is deleted ");
            }
        }
        else
        {
            File file = new File(dirname,file_Name);
            file.deleteOnExit();
            value = file.delete();
            if(value)
            {
                System.out.println();
                System.out.println("file is deleted ");
            }
        }
    }
    // this take confirmation from user for deleting the file
    void confirm() throws IOException
    {
        System.out.println("Sure u want to delete file: [yes] [no]");
      //  Scanner input = new Scanner(System.in);
       String choice = input.nextLine();
       if(choice.equalsIgnoreCase("yes"))
       {
            del();
       }
       else if(choice.equalsIgnoreCase("no"))
       {
             menu  Menu = new menu();
            Menu.option();
       }
    }
}
class menu extends delete_file
{
	// this provide option of operation to be perform on the file
    void option() throws IOException
    {
      //  Scanner input = new Scanner(System.in);
        String choice = "enter";
        
        while(choice !="exit")
        {
            System.out.println("Do you want to 1.[read] or 2.[write] or 3.[delete file] 4.[exit] ?");
            choice = input.nextLine();
            if(choice.equalsIgnoreCase("read")||choice.equals("1"))
            {
                read();// throws IOException;
            }
            if(choice.equalsIgnoreCase("write") || choice.equals("2"))
            {
                write(); //throws IOException;
            }
            if(choice.equalsIgnoreCase("delete file") || choice.equals("3"))
            {
                confirm();
                del();
               open();

            }
            if(choice.equalsIgnoreCase("exit") || choice.equals("4"))
            {
                System.exit(0);;
            }
        }
    }
}
class editor
{
    public static void main(String[] args) throws IOException
    {
        menu Menu = new menu();
        Menu.dir();
	    Menu.open();
        Menu.option();
    }
}
