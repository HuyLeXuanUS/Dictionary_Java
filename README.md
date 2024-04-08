# ---- Chương trình Từ điển US-495 ----

## 1. Thông tin liên hệ
Họ và tên: Lê Xuân Huy

MSSV: 20120495

Email: xuanhuy.tqn.005@gmail.com

SĐT: 0348324331

## 2. Cách biên dịch chương trình
2.1. Biên dịch tất cả file java

	- Bật màn hình cmd ở bên trong thư mục Dictionary
	- Gõ lệnh:    javac *.java
2.2. Chạy file XML nếu chưa có data nền

	- Buộc phải có hai thư mục bên trong Dictionary: "StorageEtoV" và "StorageVtoE"
	- Ở màn hình cmd ở bên trong thư mục Dictionary:
		+ Chạy lệnh: java ManagerStorage.ManagerEtoV, sau đó đợi chương trình render
		+ Chạy lệnh: java ManagerStorage.ManagerVtoE, sau đó đợi chương trình render
	- Lúc này các bước setup đã hoàn tất
	- Bạn cần có thư mục "StorageHistory" để lưu lịch sử tìm kiếm
2.3. Chạy ứng dụng

	- Sau khi hoàn thành các bước setup, chạy lệnh: java MyApp   để chạy ứng dụng

## 3. Link video demo

https://youtu.be/5dFSyfkLyg8

## 4.* Giải thích file XML đã chỉnh sửa

- Cả hai file đều đã được gỡ các dòng trống
- Ở phần ý nghĩa: dòng đầu tiên @word và phát âm của word, tuy nhiên một số từ sẽ không được cung cấp
Tương tự thì từ điển Việt Anh cũng không có phát âm cho tiếng việt.
- Ở các dòng sau sẽ là các định nghĩa của từ
- Ở mỗi định nghĩa sẽ bắt đầu bằng từ loại với dấu '*', tuy nhiêu ở một số cách giải thích đặt biệt,
từ loại sẽ là '!', đôi khi cũng có '+'
- Sau mỗi từ loại sẽ là các nghĩa cho từ loại đó, mỗi nghĩa bắt đầu bằng dấu '-'
- Sau mỗi nghĩa sẽ có ví dụ cho nghĩa đó, bắt đầu bằng '=', cấu trúc của nghĩa ví dụ sẽ cách nhau bằng '+'

Lưu ý: Không phải tất cả các từ đề có đầu đủ các phần trên, có từ sẽ có phát âm, có thể không. Tương tự,
có thể có từ loại hoặc không, ví dụ cũng như vậy. Cân cân nhắc để duyệt 'null' cho cấu trúc ý nghĩa.

Lưu ý: Các file data có kí tự đặc biệt sẽ bị xóa đi, vì vậy cần render lại hai thư mục "StorageEtoV" và "StorageVtoE"
cho đầy đủ, sau đó sử dụng cho file thực thi jar
