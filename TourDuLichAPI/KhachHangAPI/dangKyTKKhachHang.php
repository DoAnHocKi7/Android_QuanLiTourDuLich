<?php
    include '../connect.php';
    include '../constants.php';


    if(isset($_POST['TenKH']) && isset($_POST['DiaChiKH']) && isset($_POST['EmailKH']) 
        && isset($_POST['SdtKH']) && isset($_POST['GTinhKH']) && isset($_POST['MatKhauKH'])){
        $maKH = rand ( 0 , 1000000000 );
        $tenKH = $_POST['TenKH'];
        $diaChiKH = $_POST['DiaChiKH'];
        $emailKH = $_POST['EmailKH'];
        $sdtKH = $_POST['SdtKH'];
        $matKhauKH = $_POST['MatKhauKH'];
        $gioiTinhKH = $_POST['GTinhKH'];
        
        /*------------Kiemtra TK ton tai------------ */
        $selectKHquery = "SELECT * FROM ql_tourdulich.khach_hang 
                            where phone='$sdtKH' or email = '$emailKH' limit 1;";
        $result = $conn->query($selectKHquery);
        
        /*-------------them thong tin KH---------------*/
        if($result->num_rows==0){
            $insertKHquery = "INSERT INTO `ql_tourdulich`.`khach_hang` (`Ma_KH`, `TenKH`, `SexKH`, `Phone`, `Email`, `DiaChi`, `LoaiKH`,`MatKhau`) 
            VALUES ('$maKH', '$tenKH', '$gioiTinhKH', '$sdtKH', '$emailKH', '$diaChiKH', '".MALOAIKH_MACDINH."','$matKhauKH');";
            $conn->query($insertKHquery);
            echo "ThanhCong";
        }else{
            echo "ThatBai";
        }
    }else{
        echo "ThatBai";
    }
?>