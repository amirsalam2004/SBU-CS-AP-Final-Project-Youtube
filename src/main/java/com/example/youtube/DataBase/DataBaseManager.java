package com.example.youtube.DataBase;

import com.example.youtube.Model.*;
import com.example.youtube.Server.*;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

public class DataBaseManager {


    static Connection connection;
    static Statement statement;

    private DataBaseManager() {

    }

    /**
     * Start connection in database
     */
    private static void StartConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube", "root", "");
            statement = connection.createStatement();

        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * End  connection in database
     */
    private static void EncConnection() {
        try {
            if (connection != null) {
                statement.close();
                connection.close();

            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * get method
     */


    //get User form database in Login
    public  static  User get_User(String name, String passWord) {
        StartConnection();
        String query = "SELECT * FROM User WHERE username='%s' AND passWord='%s'";
        try {
            query = String.format(query, name, passWord);

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String idUser = rs.getString("IDuser");
                String country = rs.getString("Country");
                String password = rs.getString("passWord");
                String data = rs.getString("Time");
                String Age= rs.getString("Age");

                EncConnection();
                return new User(username, email, data, country, password, idUser,Age);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        EncConnection();
        return null;
    }


    //get Channel with  0= username  1=ID_chanel
    public static synchronized Channel get_Channel(String identifier, int number) {
        StartConnection();
        String query;
        if (number == 0) {
            query = "SELECT * FROM chanel WHERE username='%s'";
        } else {
            query = "SELECT * FROM chanel WHERE ID_chanel='%s'";
        }

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, identifier);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String ID_chanel = resultSet.getString("ID_chanel");
                String Name = resultSet.getString("Name");
                String image_Chanel = resultSet.getString("image_Chanel");
                String username = resultSet.getString("username");
                String Image_Pro = resultSet.getString("Image_Pro");
                String information = resultSet.getString("information");
                String Link = resultSet.getString("Link");
                return new Channel(ID_chanel,Name,image_Chanel,username,Image_Pro,information,Link);
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
        return null;
    }



    //this is for get video from chanel return all video in chanel
    public  synchronized static  ArrayList<Video> getList_video(String IDC) {//TODO check
        ArrayList<Video> videos = new ArrayList<>();
        StartConnection();

        String query = "SELECT * FROM video WHERE Chanel_ID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, IDC);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String path = resultSet.getString("path");
                String ID_video = resultSet.getString("ID_video");
                String Chanel_ID = resultSet.getString("Chanel_ID");
                String time_uplode = resultSet.getString("time_uplode");
                int view = resultSet.getInt("view");
                int PlayTime = resultSet.getInt("PlayTime");
                int like = resultSet.getInt("like");
                int Dis_like = resultSet.getInt("Dis_like");
                String name = resultSet.getString("name");
                String information = resultSet.getString("information");
                videos.add(new Video( ID_video, Chanel_ID, name, information, time_uplode, PlayTime, like, Dis_like, view));
            }
            EncConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return videos;
    }





    // this is for get the comment for video
    public static synchronized ArrayList<Comment> getListComment(String video_ID) {//TODO check

        StartConnection();
        String query = "SELECT * FROM  comment WHERE ID_video=? ";
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, video_ID);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String comment = resultSet.getString("comment");
                String witer = resultSet.getString("witerer");
                String ID_video = resultSet.getString("ID_video");
                int like = resultSet.getInt("like");
                int dislike = resultSet.getInt("dislike");
                String time = resultSet.getString("Time");
                String UserID = resultSet.getString("UserID");
                String id=resultSet.getString("ID");
                comments.add(new Comment(comment, UserID, witer, ID_video, time, like, dislike,id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
        return comments;

    }
    //comment that User get for his video
    public static synchronized ArrayList<Comment> getListComment_userGet(String IDU) {//TODO check
        StartConnection();
        String query = "SELECT * FROM  comment WHERE IDChanel=? ";
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, IDU);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String comment = resultSet.getString("comment");
                String witer = resultSet.getString("witerer");
                String ID_video = resultSet.getString("ID_video");
                int like = resultSet.getInt("like");
                int dislike = resultSet.getInt("dislike");
                String time = resultSet.getString("Time");
                String UserID = resultSet.getString("UserID");
                String id=resultSet.getString("ID");
                comments.add(new Comment(comment, UserID, witer, ID_video, time, like, dislike,id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
        return comments;

    }




        //get list of comment that the User send
    public static  ArrayList<Comment> getListCommentUser(String wirtter ) {//TODO check

        StartConnection();
        String query = "SELECT * FROM  comment WHERE witer=? ";
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, wirtter);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String comment = resultSet.getString("comment");
                String witer = resultSet.getString("witerer");
                String ID_video = resultSet.getString("ID_video");
                int like = resultSet.getInt("like");
                int dislike = resultSet.getInt("dislike");
                String time = resultSet.getString("Time");
                String UserID = resultSet.getString("UserID");
                String id=resultSet.getString("ID");
                comments.add(new Comment(comment, UserID, witer, ID_video, time, like, dislike,id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
        return comments;

    }


    //get list of video
    public static ArrayList<Video> getListVideoInPlayList(String IDP ){//TODO check
        StartConnection();

        ArrayList<Video> videos = new ArrayList<>();

        String query = "SELECT * FROM video   JOIN  video_playlist ON video.ID_video = video_playlist.ID_playList WHERE video_playlist.ID_playList=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, IDP);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String path = resultSet.getString("path");
                String ID_video = resultSet.getString("ID_video");
                String Chanel_ID = resultSet.getString("Chanel_ID");
                String time_uplode = resultSet.getString("time_uplode");
                int view = resultSet.getInt("view");
                int PlayTime = resultSet.getInt("PlayTime");
                int like = resultSet.getInt("like");
                int Dis_like = resultSet.getInt("Dis_like");
                String name = resultSet.getString("name");
                String information = resultSet.getString("information");
                videos.add(new Video( ID_video, Chanel_ID, name, information, time_uplode, PlayTime, like, Dis_like, view));
            }
            EncConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
        return videos;

    }
    //get list of playlist in chanel
    public static synchronized ArrayList<PlayList> getPlayList(String IDC ) {//TODO check

        StartConnection();
        String query = "SELECT * FROM playlist  JOIN  joinplaylistto_chanel ON joinplaylistto_chanel.ID_chanel = playlist.IDPlaylist WHERE joinplaylistto_chanel.ID_chanel=? ";
        ArrayList<PlayList>playLists=new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, IDC);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String idPlaylist = resultSet.getString("ID_Playlist");
                String name = resultSet.getString("name");
                String idChanel = resultSet.getString("ID_chanel");
                String image = resultSet.getString("image");
                String description=resultSet.getString("discribe");
                playLists.add(new PlayList( name, idPlaylist,idChanel, description, image));
            }
            EncConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
         return playLists;


    }




    public static synchronized ArrayList<Video> getListVideoByCategory(String category) {
        ArrayList<Video> videos = new ArrayList<>();
        StartConnection();

        String query = "SELECT * FROM video   JOIN  category_video ON video.ID_video = category_video.ID_video WHERE category_video.category=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String path = resultSet.getString("path");
                String ID_video = resultSet.getString("ID_video");
                String Chanel_ID = resultSet.getString("Chanel_ID");
                String time_uplode = resultSet.getString("time_uplode");
                int view = resultSet.getInt("view");
                int PlayTime = resultSet.getInt("PlayTime");
                int like = resultSet.getInt("like");
                int Dis_like = resultSet.getInt("Dis_like");
                String name = resultSet.getString("name");
                String information = resultSet.getString("information");
                videos.add(new Video( ID_video, Chanel_ID, name, information, time_uplode, PlayTime, like, Dis_like, view));
            }
            EncConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
        return videos;
    }

    //who you follow
    public static synchronized ArrayList<Channel> follower(String IDC){//TODO check

        ArrayList<Channel>chanels=new ArrayList<>();
        StartConnection();

        String query = "SELECT * FROM Chanel   JOIN   ON Chanel.ID_chanel = follower.IDChanelTar WHERE follower.IDChanelTar=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, IDC);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String ID_chanel = resultSet.getString("ID_chanel");
                String Name = resultSet.getString("Name");
                String image_Chanel = resultSet.getString("image_Chanel");
                String username = resultSet.getString("username");
                String Image_Pro = resultSet.getString("Image_Pro");
                String information = resultSet.getString("information");
                String Link = resultSet.getString("Link");

                chanels.add(new Channel(
                        ID_chanel,
                        Name,
                        information,
                        image_Chanel,
                        username,
                        Image_Pro,Link
                ));
            }
            EncConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }



        EncConnection();
        return chanels;

    }


    //who follow you
    public static synchronized ArrayList<Channel> following(String IDC){//TODO check

        ArrayList<Channel>chanels=new ArrayList<>();
        StartConnection();

        String query = "SELECT * FROM Chanel   JOIN   ON Chanel.ID_chanel = following.IDChanelTar WHERE following.IDChanelTar=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, IDC);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String ID_chanel = resultSet.getString("ID_chanel");
                String Name = resultSet.getString("Name");
                String image_Chanel = resultSet.getString("image_Chanel");
                String username = resultSet.getString("username");
                String Image_Pro = resultSet.getString("Image_Pro");
                String information = resultSet.getString("information");
                String Link = resultSet.getString("Link");

                chanels.add(new Channel(
                        ID_chanel,
                        Name,
                        information,
                        image_Chanel,
                        username,
                        Image_Pro,
                        Link
                ));
            }
            EncConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }



        EncConnection();
        return chanels;

    }






    /**
     * insert method
     */


//  TODO check in User
    public synchronized static boolean Cr_User(User user) {

        StartConnection();
        String query = "INSERT INTO user (username, Email, passWord, IDuser, Time,Age, Contry) values ('%s','%s','%s','%s','%s','%s','%s')";
        LocalDate localDate = LocalDate.now();

        //TODO you can set time for user now or when create a user
        query = String.format(query, user.getUsername(), user.getEmail(), user.getPassword(), user.getID(), localDate.toString(),user.getAge(), user.getCountry());

        try {
            statement.execute(query);
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        EncConnection();
        return true;
    }
    /**insert chanel
     *  */
    public synchronized static  boolean Cr_Chanel(Channel channel) {

        StartConnection();
        String query = "INSERT INTO chanel (ID_chanel,Name,information,image_Chanel,username,Image_Pro,Link) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')";
        query = String.format(query, channel.getId(), channel.getName(), channel.getDescription(), channel.getImage(), channel.getUsername(),channel.getImage_pro(),channel.getLink());
        try {

            statement.execute(query);

        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (Exception e) {
            e.getMessage();
        }


        EncConnection();
        return true;

    }
    /**insert video
     * */
    public synchronized static boolean Cr_Video(Video video) {
        StartConnection();
        for (String x : video.getHashtagsList()) {
            String query1 = "INSERT INTO category_video (category,ID_video) " +
                    "VALUES (?,?)";
            try {
                PreparedStatement statement = connection.prepareStatement(query1);
                statement.setString(1, x);
                statement.setString(2, video.getID());
                statement.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String query = "INSERT INTO video (ID_video, Chanel_ID, time_uplode, view, PlayTime, `like`, Dis_like, name, information) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, video.getID());
            statement.setString(2, video.getIDChanel());
            statement.setString(3, String.valueOf(video.getUploadTime()));
            statement.setInt(4, video.getView());
            statement.setInt(5, video.getDuration());
            statement.setInt(6, video.getLike());
            statement.setInt(7, (int) video.getDeslike());
            statement.setString(8, video.getName());
            statement.setString(9, video.getDescription());
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException ee) {
            throw new RuntimeException(ee);
        }
        EncConnection();
        return true;
    }
    /** Create PlayList  */
    public static boolean Cr_PlayList(PlayList playList) {
        StartConnection();
        ADD_playList_chanel(playList.getID(),playList.getChannelID());
        String query = "INSERT INTO playList (ID_PlayList,name,discribe,Image,ID_chanel) values ('%s','%s','%s','%s','%s')";
        query = String.format(query, playList.getID(), playList.getName(), playList.getDescription(),playList.getImage(),playList.getChannelID());
        try {
            statement.execute(query);
        } catch (SQLIntegrityConstraintViolationException er) {
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        EncConnection();
        return true;

    }
    /** insert comment */
    public static boolean Cr_comment(Comment comment) {

        StartConnection();
        String query = "INSERT INTO comment (comment,wirter,UserID,ID_video,`like`,dislike,Time) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, comment.getText());
            statement.setString(2, comment.getUserUsername());
            statement.setString(3, comment.getUserID());
            statement.setString(4, comment.getVideoID());
            statement.setInt(5, 0);
            statement.setInt(6, 0);
            statement.setString(7, String.valueOf(comment.getTime()));
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        EncConnection();
        return true;


    }
    public static boolean ADD_playList_chanel(String IDP,String IDC){
        StartConnection();
        String query="INSERT INTO joinplaylistto_chanel (ID_Playlist,ID_chanel) VALUES ('%s','%s')";
        query=String.format(query,IDP,IDC);
        try {
            statement.execute(query);

        } catch (SQLIntegrityConstraintViolationException er) {
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        EncConnection();
        return true;
    }
    public static boolean ADD_video_history (String IDV,String IDU){
        StartConnection();
        String query="INSERT INTO viode_history (IDUser,IDVideo,Time) VALUES ('%s','%s','%s')";
        LocalDateTime localDate= LocalDateTime.now();
        query=String.format(query,IDV,IDU,String.valueOf(localDate));
        try {
            statement.execute(query);

        } catch (SQLIntegrityConstraintViolationException er) {
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            EncConnection();
        }
        return true;

    }

    /** add follower or following (1=follower -- else following)  */
    public static boolean ADD_follower_following(String IDU,String IDC,int Identifier){
        StartConnection();
        String query;
        if(Ch_Follower_Following(IDU,IDC,Identifier)) {
            if (Identifier == 1) {
                query = "INSERT INTO follower (IDChanel,IDuser) VALUES ('%s','%s')";
            } else
                query = "INSERT  INTO following  (IDChanel,IDuser) VALUES ('%s','%s')";

            query=String.format(query,IDC,IDU);
            try {
                statement.execute(query);

            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }

        EncConnection();
        return true;

    }

    private  static boolean Ch_Follower_Following(String IDU,String IDC,int Identifier) {
        String query;

        if (Identifier==1) {
            query = "SELECT * FROM follower WHERE IDChanel='%s' AND IDuser ='%s'";
        }else{
            query = "SELECT * FROM following WHERE IDChanel='%s' AND IDuser ='%s'";

        }
        query=String.format(query,IDC,IDU);
        try {
            ResultSet resultSet=  statement.executeQuery(query);
            if (!resultSet.next()){
                System.out.println("1231231");
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;

    }

    public synchronized static boolean Karma(int Karma, String UserId, String Video) {
        StartConnection();
        String query;
        if (!CheckKarma(UserId, Video)) {
            query = "INSERT INTO Karma (karma, IDUser, IDVideo) VALUES (?, ?, ?)";
        } else {
            query = "UPDATE Karma SET karma = ? WHERE IDUser = ? AND IDVideo = ?";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Karma);
            statement.setString(2, UserId);
            statement.setString(3, Video);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            EncConnection();
        }
        return true;
    }

    private static boolean CheckKarma(String UserId, String Video) {
        String query;
        boolean exists;

        query = "SELECT COUNT(*) FROM Karma WHERE IDUser = ? AND IDVideo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, UserId);
            statement.setString(2, Video);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            exists = count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }

        return exists;
    }

    /**
     * delete
     */


    //this method delete a video in all playlist and history and chanel
    public static void DE_Video(String idV, String idC) {
        StartConnection();
        delete_Video_chanel(idV,idC);
        delete_Video_history( idV);
        delete_Video_Playlist(idV );
        delete_Video(idV);
        delete_Video_category(idV);
        EncConnection();
    }

    private static void delete_Video_Playlist(String IDV    ) {
        String query = "DELETE FROM video_playlist WHERE IDvideo ='%s'  ";
        query = String.format(query, IDV);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void delete_Video_chanel(String IDV,String IDC){
        String query = "DELETE FROM video_chanel WHERE ID_video ='%s' AND    ID_chanel='%s' ";
        query = String.format(query, IDV, IDC);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static  void delete_Video_history(String idV){
        String query = "DELETE FROM viode_history  WHERE IDVideo ='%s'  ";
        query = String.format(query, idV);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static  void delete_Video(String idV){
        String query = "DELETE FROM video  WHERE ID_video ='%s'  ";
        query = String.format(query, idV);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static  void delete_Video_category(String idV){
        String query = "DELETE FROM category_video  WHERE ID_video ='%s'  ";
        query = String.format(query, idV);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * delete play list so delete playList and Video_playlist
     * @param idp
     */

    public static void deletePlayList(String idp) {
        StartConnection();
        String query = "DELETE FROM playlist WHERE ID_Playlist ='%s'";
        String query1="DELETE FROM video_playlist WHERE ID_playlist ='%s'";
        query = String.format(query, idp);
        query1 = String.format(query1, idp);
        try {
            statement.execute(query);
            statement.execute(query1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        EncConnection();
    }


    //this method delete all comments in vidoe
    public static void delete_Comment_ALL(String IDV) {

        StartConnection();
        String query = "DELETE FROM comment WHERE ID_video='%s'  ";
        query = String.format(query,IDV);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        EncConnection();
    }


    //this method delete comment for writer
    public static void delete_Comment_writer(String IDV,String IDCom) {
        StartConnection();
        String query = "DELETE FROM comment WHERE wirter ='%s'  AND IDcommet='%s'";
        query = String.format(query,IDV,IDCom);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        EncConnection();
    }

    //Only the person who posted the video can delete comment
    public static void delete_comment_User(String IDU,String IDCom){
        StartConnection();
        String query = "DELETE FROM comment WHERE UserID ='%s'  AND IDcommet='%s'";
        query = String.format(query,IDU,IDCom);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        EncConnection();

    }


    public static boolean deleteFormHistory(String IDU, String IDV, String action, int Min) {
        StartConnection();
        String query = "";

        if (action.equals("1")) {
            query = "DELETE FROM viode_history WHERE IDVideo = ? AND idUser = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, IDV);
                ps.setString(2, IDU);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("2")) {
            query = "DELETE FROM viode_history WHERE idUser = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, IDU);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("3")) {
            query = "SELECT * FROM viode_history WHERE idUser = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, IDU);
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        LocalDateTime localDateTime = LocalDateTime.parse(resultSet.getString("Time"));
                        int hourDiff = LocalDateTime.now().getMinute() - localDateTime.getMinute();
                        System.out.println(hourDiff);
                        if (hourDiff > Min) {
                            String deleteQuery = "DELETE FROM viode_history WHERE IDVideo = ? AND idUser = ?";
                            try (PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
                                deletePs.setString(1, resultSet.getString("IDVideo"));
                                deletePs.setString(2, resultSet.getString("idUser"));
                                deletePs.executeUpdate();
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        EncConnection();
        return true;
    }
    public static boolean UnFollow(String IDU,String IDC,int Identifier){
        StartConnection();
        String query ;
        if (Identifier == 1) {
            query = "DELETE FROM follower WHERE IDuser='%s' AND IDChanel='%s'";
        } else
            query = "DELETE FROM following  WHERE IDuser='%s'AND IDChanel='%s'";
        query=String.format(query,IDU,IDC);

        try {
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
            return false;

        }
        EncConnection();
        return true;


    }




    /**
     * update method
     */

    public static boolean CH_PassWordUser(String IDU, String PassWord) {//TODO check
        StartConnection();

        if (!isUserExists(IDU)) {
            return false;
        }
        String query = "UPDATE User SET passWord = ? WHERE IDuser = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, PassWord);
            statement.setString(2, IDU);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            EncConnection();
        }

        return true;
    }
    public static boolean isUserExists(String IDU) {
        StartConnection();
        String query = "SELECT COUNT(*) FROM User WHERE IDuser = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, IDU);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            EncConnection();
        }
        return false;
    }
    public static  boolean CH_UserName(String IDU,String Username){
        StartConnection();
        String query1="UPDATE chanel SET username  = ? WHERE username =?";

        try (PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setString(1,Username );
            statement.setString(2, IDU);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            EncConnection();
        }
        try (PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setString(1,Username);
            statement.setString(2, Username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            EncConnection();
        }




        return true;

    }


    //this method for change the email
    public static boolean CH_Email(String IDU,String Email ){//TODO CHECK
        StartConnection();

        if (!isUserExists(IDU)) {
            return false;
        }
        String query = "UPDATE User SET Eamil = ? WHERE IDuser = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Email);
            statement.setString(2, IDU);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            EncConnection();
        }

        return true;
    }

    //change the name of Playlist
    public static synchronized boolean UP_Name_Playlist(String name,String IDP){//TODO CHECK
        StartConnection();
        String query = "UPDATE playList SET name ='%s'  WHERE ID_Playlist='%s'";
        query = String.format(query, name,IDP);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            EncConnection();
        }
        return false;

    }

    public static synchronized boolean UP_view_Playlist(String IDP){//TODO CHECK
        StartConnection();
        String query = "UPDATE playList SET view=view+1  WHERE ID_Playlist='%s'";
        query = String.format(query,IDP);
        try {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            EncConnection();
        }
        return true;
    }


    /**
     * update comment
     * @param Describe
     * @param IDC
     * @return
     */
    public static synchronized boolean UP_Describe_Comment(String Describe,String IDC){//TODO CHECK
        StartConnection();
        String query = "UPDATE comment  SET wirter ='%s'  WHERE IDcommet = '%s'";
        query = String.format(query,Describe,IDC);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            EncConnection();
        }
        return true;

    }
    public static synchronized boolean UP_Like_Comment(String IDC){//check  true
        StartConnection();
        String query = "UPDATE comment  SET LikeComment = LikeComment + 1  WHERE IDcommet = '%s'";
        query = String.format(query,IDC);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            EncConnection();
        }
        return true;
    }
    public static boolean UP_DisLike_Comment(String IDU) {//check true
        StartConnection();
        String query = "UPDATE comment SET dislike = dislike + 1 WHERE IDcommet = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, IDU);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            EncConnection();
        }
    }
    public static boolean UP_Comment_Comment(String IDU,String comment ){//check true
        StartConnection();
        String query = "UPDATE comment SET comment='%s' WHERE IDcommet = '%s'";
        query=String.format(query,comment,IDU);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            EncConnection();
        }
        return true;
    }
    /** Update chanel */

    public static synchronized boolean UP_Name_Chanel(String IDC,String name){//TODO check
        StartConnection();
        String query = "UPDATE chanel SET Name='%s' WHERE ID_chanel = '%s'";
        query=String.format(query,name,IDC);
        try {
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            EncConnection();
        }


        return true;
    }


    public static synchronized boolean UP_information_Chanel(String information,String IDC){//TODO check it
        StartConnection();
        String query = "UPDATE chanel SET Name='%s' WHERE ID_chanel = '%s'";
        query=String.format(query,information,IDC);
        try {
            statement.execute(query);
        }catch (SQLException r)
        {
            r.printStackTrace();
            return false;
        }
        finally {
            EncConnection();
        }
        return true;
    }
    public static synchronized boolean UP_Link_Chanel(String Link,String IDC){//TODO Check it
        StartConnection();
        String query = "UPDATE chanel SET Link = Link + '#%s' WHERE IDcommet = ?";
        try {
            query=String.format(query, Link,IDC);
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            return true;
        }
    }


    public static synchronized boolean UP_Username_Chanel(String Username,String IDC){
        StartConnection();
        String query = "UPDATE chanel SET username='%s' WHERE ID_chanel = '%s'";
        try {
            query =String.format(query,Username,IDC);
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            EncConnection();
        }
        return true;
    }


    /**
     * check method
     */

    /**
     Search  in data base
     *
     */

    public static synchronized ArrayList<Video> SE_video (String Word,String Way){
        StartConnection();
        ArrayList<Video> Videos=new ArrayList<>();
        String query;
        ResultSet resultSet;
        try {
            if (Way.equals("informatin")){
                query = "SELECT * " +
                        "FROM Video " +
                        " WHERE  information REGEXP ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }
            else if(Way.equals("name")){
                query = "SELECT * " +
                        "FROM Video " +
                        " WHERE  name REGEXP ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }
            else {
                query = "SELECT * " +
                        "FROM Video " +
                        " WHERE  information REGEXP ? "
                        +"OR name REGEXP ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                statement.setString(2,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }



            // Process the results
            while (resultSet.next()) {
                Videos.add(new Video(resultSet.getString("ID_video"),
                        resultSet.getString("Chanel_ID"),
                        resultSet.getString("name"),
                        resultSet.getString("information"),
                        resultSet.getString("time_uplode"),
                        resultSet.getInt("PlayTime"),
                        resultSet.getInt("like"),
                        resultSet.getInt("Dis_like"),
                        resultSet.getInt("view")
                )) ;

            }


        }catch (SQLException r){
            r.printStackTrace();
        }finally {
            EncConnection();
        }
        return Videos;
    }



    //get chanel by name informatin and
    public static synchronized ArrayList<Channel>SE_Chanel(String Word,String Way){//CHECK IT
        StartConnection();
        ArrayList<Channel> Chanel=new ArrayList<>();
        String query;
        ResultSet resultSet;

        try {
            if (Way.equals("informatin")){
                query = "SELECT * " +
                        "FROM Chanel " +
                        " WHERE  information REGEXP ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }
            else if(Way.equals("name")){
                query = "SELECT * " +
                    "FROM Chanel " +
                    " WHERE  name REGEXP ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }
            else {
                query = "SELECT * " +
                        "FROM Chanel " +
                        " WHERE  information REGEXP ? "
                        +"OR name REGEXP ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                statement.setString(2,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }


            // Process the results
            while (resultSet.next()) {
                Chanel.add(new Channel(resultSet.getString("ID_chanel"),
                        resultSet.getString("name"),
                        resultSet.getString("information"),
                        resultSet.getString("imageChanel"),
                        resultSet.getString("username"),
                        resultSet.getString("imagePro"),
                        resultSet.getString("link")
                        )) ;

            }


        }catch (SQLException r){
            r.printStackTrace();
        }finally {
            EncConnection();
        }
        return Chanel;
    }
    public static  synchronized  ArrayList<PlayList>SE_playLists(String Word ,String Way){
        String query;
        ResultSet resultSet;
        ArrayList<PlayList>playLists=new ArrayList<>();
        StartConnection();
        try {
            if (Way.equals("name")) {
                query = "SELECT * " +
                        "FROM playList " +
                        " WHERE  name REGEXP ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }else if(Way.equals("discribe")){
                query = "SELECT * " +
                        "FROM playList " +
                        " WHERE  discribe REGEXP ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }
            else {
                query = "SELECT * " +
                        "FROM playList " +
                        " WHERE  discribe REGEXP ? "+
                        "OR name REGEXP ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                statement.setString(2,  "([A-Z]|[0-9])?"+Word+"([A-Z]|[0-9])?");
                resultSet = statement.executeQuery();
            }
            while (resultSet.next()){
                playLists.add(new PlayList(
                        resultSet.getString("name"),
                        resultSet.getString("ID_Playlist"),
                        resultSet.getString("ID_chanel"),
                        resultSet.getString("discribe"),
                        resultSet.getString("image")
                        ));
            }


        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }finally {
            EncConnection();
        }



        return playLists;

    }

    /**
     * Save Play list
     */

    public static synchronized boolean SA_playlist(String IDP,String IDU){//TODO check it
        StartConnection();
        String query="INSERT INTO savePlaylist (ID_Playlist,ID_user) VALUSE ('%s','%s')";
        query=String.format(query,IDP,IDU);

        try {
            statement.execute(query);
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }finally {
            EncConnection();
        }
        return true;
    }
    public static synchronized boolean SA_Video(String IDV,String IDU){//TODO check it
        StartConnection();
        String query="INSERT INTO saveVidoe (ID_Playlist,ID_user) VALUSE ('%s','%s')";
        query=String.format(query,IDV,IDU);

        try {
            statement.execute(query);
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }finally {
            EncConnection();
        }
        return true;
    }

    /**
     * udpate the taste table
     */

    private static synchronized void VIEW_video(){

    }
    private static synchronized void Like_video(){

    }
    private static synchronized void DisLike_video(){

    }












}
