
Selamlar Kodun ihtiyac olanı kadarını yazdım. Fakat daha fazlası yapılabilir bu noktaları not düştüm.

Relationları entity üzerinde tutmadım çünkü user viewsleri çekerken cok fazla data geleceği için baglantıları idler
üzerinden kurdum.

Do you delete any data from the database?

Bu sorunun cevabı buradaki dataları silmiyoruz çünkü ilerde bizim anlamlı datalar cıkarabilmemizi sağlayacaktır.
Fakat UserViewEntityde showStatus alanı tuttum bu datayı tamamen pasife cekmek için kullanılacaktır.

Do you have any periodic task type of batch jobs to maintain
data?
■ If yes, Why needed?
■ If no, Why not needed?

Evet benim kurduğum sistemde kullancıların görüntülemelerinde 30 günden aşağıda kalan görüntülemeler otomatikmen pasife
cekilip hiç bir sekilde gösterilmemek için yukarıdaki bahsettiğim showStatus parametresi false yapılarak hiç gösterilmemesi sağlanır

Ek olarak Service testlerini yazdım. Controllerlar için integration testleri yazmadım.

Bunun dışında userların view işlemlerinde kafka kullanmak queue mantığı için doğru olacaktır. Kafka entegrasyonu yapmadım.
Bunun dışında listeleme servislerinde user detayı için cache kullanılabilir. Fakat listelemedeki userview type için cache yapmak doğru olmayacaktır.

Test covarage için sonuc 100% classes,%83 lines covered şeklindedeir %100 olmamasının nedeni controllerlara test yazmamamdır.