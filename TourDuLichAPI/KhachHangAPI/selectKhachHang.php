<?php
    include '../connect.php';
    include '../constants.php';
    if(isset($_GET['tenKH'])){
        $tenKH = $_GET['tenKH'];
        
        /*-------------them thong tin KH---------------*/
        $selectKHquery = "SELECT * FROM ql_tourdulich.khach_hang where phone='$tenKH' or email = '$tenKH' limit 1;";
        $result = $conn->query($selectKHquery);
        $arr=array();

        if($result->num_rows>0){

            while($row=$result->fetch_assoc()){

     
                $arr[]=$row;
            }
        }
        echo json_encode($arr);

    }
?>