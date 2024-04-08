# Nova Direct Installer の使い方

---

> [!NOTE]
> 現在、移行作業中！

---

## 警告

> [!CAUTION]
> このツールの使用に依って生じた損害については一切責任を負いません｡  
また､ チャレンジパッド３の場合は､ 通常は [**MTKClientを使用した方法**](https://zenn.dev/s1204it/articles/16fce85441821f) を利用し､ どうしてもPC環境が無い時のみ利用して下さい｡

## 必要なもの

- チャレンジパッド ２ / ３ / Neo / Next  
> [!IMPORTANT]
> **※初期化直後の状態のものを使用して下さい**

> [!NOTE]
> チャレンジパッド２の場合は次のビルド番号のみ使用可能です。  
> #### TAB-A03-BS
> - 01.16.000
> #### TAB-A03-BR / BR2 / BR2B
> - 02.02.000
> - 02.04.000
>
> それ以前のビルドでは署名チェック機能が実装されているため、NovaDirectInstaller を使用する事は出来ません。
- microSDカード
- SDカードを読み書き可能な端末

## セットアップ

⒈ 次のリンクを開き､ ファイルとして保存します｡  
[**test_environment_info.xml**](https://gh.s1204.me/NovaDirectInstaller/test_environment_info.xml)

⒉ SDカードの最上層に､ `test_environment_info.xml` をコピーします｡  

⒊ チャレンジパッドにmicroSDカードを挿入します｡

⒋ ホーム画面の､ スタートボタンを押します｡

[![](https://github.com/s1204IT/NovaDirectInstaller/assets/52069677/7b570b1d-60b5-4186-8080-4fbdde7e6e9c)](#)

⒌ ｢<kbd><b>わかった</b></kbd>｣ を押します｡

> [!NOTE]
> USB または ACアダプターが接続されていないと続行できません｡  
また､ バッテリー残量が50%未満だと続行できません｡  

⒍ 画面右下に黒文字で改造が有効になっている旨の文章が在る事を確認します｡  

ここで通常と表示が変わらない場合は､ microSDカードが認識出来ていないか､ ファイルが正しく設定されていません｡

確認出来たら､ ｢<kbd><b>設定を始める</b></kbd>｣→｢<kbd><b>近くのネットワークから接続する</b></kbd>｣ からWi-Fiに接続します｡  
接続後､ ｢<kbd><b>←もどる</b></kbd>｣→｢<kbd><b>次へ</b></kbd>｣→｢<kbd><b>次へ</b></kbd>｣と進みます｡

⒎ 正常に続行すると以下のような画面が出ます｡

[![](https://github.com/s1204IT/NovaDirectInstaller/assets/52069677/8b883890-c60a-4043-9636-ea90e6730516)](#)
**画面には触れずに** そのままお待ちください｡  
内部処理が終了後､ 自動的に再起動されます｡

⒏ 再起動後､ **Nova Launcher** が起動します。

> [!WARNING]
> チャレンジパッド２ / ３の場合､ 起動時に｢`ホームアプリを選択`｣のポップアップが出るので､ ｢<kbd><b>配布アプリ</b></kbd>｣を選択後､ ｢<kbd><b>常時</b></kbd>｣を選択して続行します｡

これで簡易的な改造は完了です｡

---

## Dhizuku について

[**Dhizuku**](https://github.com/iamr0s/Dhizuku) とは、端末所有者の権限を共有するためのアプリです。

[**カスタマイズツール**](https://github.com/Kobold831/CPadCustomizeTool)、[**Aurora Store**](https://gitlab.com/AuroraOSS/AuroraStore)、[**Dhizuku DPC**](https://github.com/Kobold831/DhizukuDPC)([Test DPC](https://github.com/googlsamples/android-testdpc) ベース) 間で権限を共有できます。

**Dhizuku** の機能を使用するには ADB が必要です。
```
adb shell dpm set-device-owner com.rosan.dhiziku/.server.DhizukuDAReceiver
```
