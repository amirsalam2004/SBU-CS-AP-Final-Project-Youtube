package com.example.youtube.DataBase;

import com.example.youtube.Model.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // get
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
                String country = rs.getString("Contry");
                String password = rs.getString("passWord");
                String data = rs.getString("Time");

                EncConnection();
                return new User(username, email, data, country, password, idUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        EncConnection();
        return null;
    }


    //get Channel with  0= username  1=UUID

    public static synchronized Channel get_Channel(String identifier, int number) {
        StartConnection();
        String query;
        if (number == 0) {
            query = "SELECT * FROM chanel WHERE username='%s'";
        } else {
            query = "SELECT * FROM chanel WHERE ID_chanel='%s'";
        }

        try {
            query = String.format(query, identifier);

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String username = rs.getString("username");
                String name = rs.getString("Name");
                String image = rs.getString("image");
                String ID_chanel = rs.getString("ID_chanel");
                String information = rs.getString("information");
                EncConnection();
                return new Channel(ID_chanel, name, information, username, image);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
        return null;
    }



    //this is for get video from chanel return all video in chanel
    public static  ArrayList<Video> getList_video(String chanel) {
        ArrayList<Video> videos = new ArrayList<>();
        StartConnection();

        String query = "SELECT * FROM video WHERE Chanel_ID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, chanel);
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





    // this is for get the comment
    public static synchronized ArrayList<Comment> getListComment(String video_ID) {

        //TODO 2 way for this 1-add column in comment table or join with user table
        StartConnection();
        String query = "SELECT * FROM  comment WHERE ID_video=? ";
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
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
    public static  ArrayList<Comment> getListCommentUser(String user ) {

        StartConnection();
        String query = "SELECT * FROM  comment WHERE UserID=? ";
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
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
    public static ArrayList<Video> getListVideoInPlayList(String IDP ){
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

    public static synchronized ArrayList<PlayList> getPlayList(String IDC ) {

        StartConnection();
        String query = "SELECT * FROM playlist  JOIN  joinplaylistto_chanel ON joinplaylistto_chanel.ID_chanel = playlist.IDPlaylist WHERE joinplaylistto_chanel.ID_chanel=? ";
        ArrayList<PlayList>playLists=new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, IDC);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String path = resultSet.getString("path");
                String ID_video = resultSet.getString("ID_video");
                String Chanel_ID = resultSet.getString("Chanel_ID");
                String time_uplode = resultSet.getString("time_uplode");
//                playLists.add(new Video())
            }
            EncConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            EncConnection();
        }
        //TODO complete this part
         return null;


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


    /**
     * insert method
     */

    /**insert User
     *
      */
//    TODO check in User
    public synchronized static boolean insertUser(User user) {

        StartConnection();
        String query = "INSERT INTO user (username, Email, passWord, IDuser, Time, Contry) values ('%s','%s','%s','%s','%s','%s')";
        LocalDate localDate = LocalDate.now();

        //TODO you can set time for user now or when create a user
        query = String.format(query, user.getUsername(), user.getEmail(), user.getPassword(), user.getID(), localDate.toString(), user.getCountry());

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
    public synchronized static  boolean insertChanel(Channel channel) {

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
    public synchronized static boolean insertVideo(Video video) {
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
    public static boolean CreatePLayList(PlayList playList) {
        StartConnection();
        AddPlayList(playList.getID(),playList.getChannelID());
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
    public static boolean insertComment(Comment comment) {

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
    public static boolean AddPlayList(String IDP,String IDC){
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
    public static boolean insertIntoHistory (String IDV,String IDU){
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
    public static boolean addToSubscriberORing(String IDU,String IDC,int Identifier){
        StartConnection();
        String query;
        if(checkINFollwer(IDU,IDC,Identifier)) {
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

    private  static boolean checkINFollwer(String IDU,String IDC,int Identifier) {
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

    public synchronized static boolean Karam(int Karma, String UserId, String Video) {
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
    public static void delete_Video(String idV, String idC) {
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

    //update user information
    public static void UpdateUserInformation(User user) {
        StartConnection();
        String query = "UPDATE User SET username = '%s', Email = '%s', passWord = '%s', Contry = '%s' WHERE IDuser = '%s'";
        query = String.format(query, user.getUsername(), user.getEmail(), user.getPassword(), user.getCountry(), user.getID());
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        EncConnection();
    }


    public static void UpdateChanelInfromation(Channel channel){
        StartConnection();
        String query = "UPDATE Chanel SET username = '%s', image = '%s', Name  = '%s', information = '%s' WHERE ID_chanel = '%s'";
        query = String.format(query, channel.getUsername(), channel.getImage(), channel.getDescription(), channel.getName(), channel.getId());
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            EncConnection();
        }

    }
    public static synchronized boolean UpdatPlayListInfromation(PlayList playList){
        StartConnection();
        String query = "UPDATE playList SET name ='%s' ,discribe ='%s' WHERE ID_Playlist='%s'";
        query = String.format(query, playList.getName(), playList.getDescription(), playList.getID());
        try {
            statement.execute(query);
            EncConnection();
            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }finally {
            EncConnection();
        }
        return false;

    }

    public static boolean UpdatChanelInfromation(Channel channel){
        StartConnection();
        String query = "UPDATE chanel SET Name ='%s' ,information ='%s' ,image ='%s',username ='%s'  WHERE ID_chanel='%s'";
        query = String.format(query, channel.getName(), channel.getDescription(), channel.getImage(),channel.getUsername(),channel.getId());
        try {
            statement.execute(query);
            EncConnection();
            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }



        return false;

    }

    /**
     Search  in data base
     *
     */












}
