<?php
    include '../../connect.php';
    $userName="";
    $password="";
    $ten="";
    $email="";
    $sdt="";
    $gioiTinh="";
    $diaChi="";
    $loaiTK="";
    if(isset($_POST['username'])){
        $userName = $_POST['username'];
    }
    if(isset($_POST['ten'])){
        $ten = $_POST['ten'];
    }
    if(isset($_POST['password'])){
        $password = $_POST['password'];
    }
    if(isset($_POST['email'])){
        $email = $_POST['email'];
    }
    if(isset($_POST['sdt'])){
        $sdt = $_POST['sdt'];
    }
    if(isset($_POST['gioiTinh'])){
        $gioiTinh = $_POST['gioiTinh'];
    }
    if(isset($_POST['diaChi'])){
        $diaChi = $_POST['diaChi'];
    }
    // if(isset($_POST['sex'])){
    //     $sex = $_POST['sex'];
    // }
    if(isset($_POST['loaiTK'])){
        $loaiTK = $_POST['loaiTK'];
    }

    if(!(empty($userName)&&empty($email)&&empty($password)&&empty($sdt)
        &&empty($sex)&&empty($diaChi)&&empty($loaiTK)&&empty($ten))){
            $sql = "INSERT INTO account (username, Email, Password, Phone, Sex, Address, LoaiTK, ten) 
            VALUES ('$userName', '$email', '$password', '$sdt', '$gioiTinh', '$diaChi', '$loaiTK', '$ten');";
            $result = $conn->query($sql);
        }
  
    
?>