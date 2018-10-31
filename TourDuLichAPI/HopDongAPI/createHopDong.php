<?php 
    require_once '../PHPMailer-5.2.25/PHPMailerAutoload.php';
    include '../connect.php';
    define('MALOAIKH_MACDINH','4');
    define('PHUTHU','0');
    define('GHICHU','Không có');
    define('TINHTRANG_DONHANG',1);
    if(isset($_POST['TenKH']) && isset($_POST['DiaChiKH']) && isset($_POST['EmailKH']) 
        && isset($_POST['SdtKH']) && isset($_POST['GTinhKH']) && isset($_POST['NgayDH'])
        && isset($_POST['TongSoNguoi']) && isset($_POST['MaTour']) && $_POST['SoNguoi']){

        if(isset($_POST['MaKH'])){
            $maKH=$_POST['MaKH'];
        }else{
            $maKH = rand ( 0 , 1000000000 );
            $tenKH = $_POST['TenKH'];
            $diaChiKH = $_POST['DiaChiKH'];
            $emailKH = $_POST['EmailKH'];
            $sdtKH = $_POST['SdtKH'];
            $gioiTinhKH = $_POST['SdtKH'];
                    /*-------------them thong tin KH---------------*/
        $insertKHquery = "INSERT INTO `ql_tourdulich`.`khach_hang` (`Ma_KH`, `TenKH`, `SexKH`, `Phone`, `Email`, `DiaChi`, `LoaiKH`) 
                            VALUES ('$maKH', '$tenKH', '$gioiTinhKH', '$sdtKH', '$emailKH', '$diaChiKH', '".MALOAIKH_MACDINH."');";
        $conn->query($insertKHquery);    
        }
        
        $ngayDH = $_POST['NgayDH'];
        $soNguoi = $_POST['SoNguoi'];
        $tongSoNguoi = $_POST['TongSoNguoi'];
        $maTour = $_POST['MaTour'];

        /*-------------Them hop dong-------------------*/
        $maHopDong = rand ( 0 , 100000 ) . $ngayDH;
        $insertHopDongquery = "INSERT INTO `ql_tourdulich`.`hop_dong` (`MaHopDong`, `NgayDat`, `MaKH`, `PhuThu`, `GhiChu`) 
                    VALUES ('$maHopDong', '$ngayDH', '$maKH', '".PHUTHU*$tongSoNguoi."', '".GHICHU."');";
        $conn->query($insertHopDongquery);
        /*-------------Them Chi Tiet hop dong-------------------*/
        $insertCTHopDongQuery = "INSERT INTO `ql_tourdulich`.`ct_hopdong` (`MaTour`, `TINHTRANG`, `GhiChu`, `MaHopDong`, `SLNguoi`) 
                                VALUES ('$maTour', '".TINHTRANG_DONHANG."', '".GHICHU."', '$maHopDong', '$soNguoi');";
        $conn->query($insertCTHopDongQuery);
        echo $maHopDong;
    }    

    
?> 
 