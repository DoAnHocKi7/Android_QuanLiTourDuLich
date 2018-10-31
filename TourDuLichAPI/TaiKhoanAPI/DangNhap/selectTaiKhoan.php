<?php
    include '../../connect.php';
//     $sql = 'SELECT * from account';
//     $result = $conn->query($sql);
//     $arr=array();
//    if($result->num_rows>0){
//        while($row=$result->fetch_assoc()){
//            $arr[]=$row;
//        }
//    }
//     echo json_encode($arr);
    if(isset($_POST['Username'])&&isset($_POST['Password'])){
        $username = $_POST['Username'];
        $password = $_POST['Password'];
        $selectQuery = "select count(ma_kh) from khach_hang where matkhau='$password' 
                        and(email='$username'||phone='$username')";
        $result =  $conn->query($selectQuery);
            echo $result->num_rows;
    }else{
        echo 0;
    }

?>