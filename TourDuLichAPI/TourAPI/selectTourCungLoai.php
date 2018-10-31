<?php
    include '../connect.php';
    if(isset($_GET['LoaiTour'])){
        $loaiTour = $_GET['LoaiTour'];
        $sql = "SELECT Matour,TenTour,HinhAnh,Gia,MoTa,TenDiaDiem,LoaiTour,MoTa,tour.MaKS,tenks,diachi,
        giatien,MaLoaiKS,ngaykhoihanh,ngayketthuc 
        from tour,hanh_trinh,diadiem,khach_san
        where tour.Ma_HT=hanh_trinh.ma_HT and hanh_trinh.Noidi=diadiem.MaDiaDiem
                and khach_san.maks=tour.maks and LoaiTour = '$loaiTour' limit 5";

        $result = $conn->query($sql);
        $arr=array();
        if($result->num_rows>0){
            while($row=$result->fetch_assoc()){
                    try {
                        $path=ADDRESS . $row['MoTa'];
                        $file_headers = @get_headers($path);
                        if ($file_headers[0] == 'HTTP/1.0 404 Not Found'){ // or "HTTP/1.1 404 Not Found" etc.
                            throw new Exception();
                        }
                        $myfile = fopen($path, 'r') or die('Không thể mở file!');
                        $moTa = fread($myfile,getFileSize($path));
                        if(!empty($moTa)){
                            $row['MoTa'] = $moTa;
                        }
                        else{
                            $row['MoTa'] = 'Chưa có mô tả cho tour du lịch này';
                        }
                        fclose($myfile);

                    } catch (Exception $e) {
                        $row['MoTa'] = 'Chưa có mô tả cho tour du lịch này';
                    }

                $arr[]=$row;
            }
        }
        echo json_encode($arr);
    }
    

    function getFileSize($path){
        $ch = curl_init($path);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
        curl_setopt($ch, CURLOPT_HEADER, TRUE);
        curl_setopt($ch, CURLOPT_NOBODY, TRUE);       
        $data = curl_exec($ch);
        $size = curl_getinfo($ch, CURLINFO_CONTENT_LENGTH_DOWNLOAD);        
        curl_close($ch);
        return $size;
    }

?>