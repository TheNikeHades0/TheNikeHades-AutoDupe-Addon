<p align="center">
  <img src="indir-2.jpg" alt="TheNikeHades-AutoDupe-Banner" width="800">
</p>

<p align="center">
  <a href="https://github.com/TheNikeHades0/TheNikeHades-AutoDupe-Addon/releases">
    <img src="https://img.shields.io/github/v/release/TheNikeHades0/TheNikeHades-AutoDupe-Addon?style=for-the-badge&color=blue" alt="Release">
  </a>
  <a href="https://github.com/TheNikeHades0/TheNikeHades-AutoDupe-Addon/stargazers">
    <img src="https://img.shields.io/github/stars/TheNikeHades0/TheNikeHades-AutoDupe-Addon?style=for-the-badge&color=yellow" alt="Stars">
  </a>
</p>

# 🔱 TheNikeHades-AutoDupe-Addon (1.21.4)

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Meteor](https://img.shields.io/badge/Meteor%20Client-1.21.4-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Ultra%20Fast-red?style=for-the-badge)

**TheNikeHades-AutoDupe-Addon**, Meteor Client için geliştirilmiş, piyasadaki en hızlı ve stabil **Multi-Tasking** destekli Item Frame Dupe otomasyonudur. 1.21.4 sürümleri için tamamen optimize edilmiş ve performans odaklı kodlanmıştır.

## ✨ Öne Çıkan Özellikler

* 🚀 **Multi-Tasking:** Tek bir tick içerisinde menzildeki tüm çerçevelere paket gönderir. Birden fazla frame ile saniyeler içinde binlerce eşya dupe yapabilirsiniz.
* 🛡️ **Anti-Break System:** Çerçeve boşaldığı anda vuruşu kesen akıllı paket kontrolü ile çerçevelerin kırılma ihtimalini ortadan kaldırır.
* 🔄 **Smart Replace:** Kırılan veya eksilen çerçeveleri eski koordinatlarına saniyeler içinde geri yerleştirir.
* 🎨 **Nike Visuals:** Özelleştirilebilir render kutuları ile dolu/boş çerçeveleri kolayca takip edin.
* ⏱️ **Zero Delay Mode:** `Tick Delay` ayarını 0 yaparak sunucu hızına göre maksimum performansa ulaşın.

## 🛠️ Kurulum

1. **Meteor Client**'ın en güncel 1.21.4 sürümünü kurun.
2. [Releases](../../releases) sekmesinden `TheNikeHades-AutoDupe-Addon.jar` dosyasını indirin.
3. İndirdiğiniz dosyayı `.minecraft/mods` klasörüne atın.
4. Oyun içinde **TheNikeHades-Dupe** modülünü aktif edin.

## 💻 Geliştiriciler İçin Derleme

Projeyi kendi bilgisayarınızda derlemek isterseniz:

```bash
# Depoyu klonlayın
git clone [https://github.com/TheNikeHades0/TheNikeHades-AutoDupe-Addon.git](https://github.com/TheNikeHades0/TheNikeHades-AutoDupe-Addon.git)

# Derleyin (Testleri atlamak için -x test parametresini kullanın)
./gradlew clean build -x test --no-daemon
