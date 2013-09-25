# tagプラグイン #

Viewテンプレートのカスタムタグを提供します。

## 動的参照タグ ##

tagプラグインで提供しているタグの中には、 __動的参照タグ__ というものがあります。
__動的参照タグ__ とはHTMLのimgタグなどリソースを指定するタグにおいて、
Playの開発/本番のモードによって参照先を動的に変えるタグのことです。

開発/本番の参照先は以下となります。

* 開発の参照先
 * /public
 * 例)`<img src="/public/images/sample.jpg"/>`
* 本番の参照先
 * CDNサーバ(cloudfront)のURL
 * 例)`<img src="http://d2gba02i1vlqqa.cloudfront.net/20120331122431/images/sample.jpg"/>`

動的参照タグを __本番モード__ で利用する場合、通常の準備の他に以下の設定を行う必要があります。

1.application.confにCDNサーバ(cloudfront)のドメイン名を設定する

`cloudfront.domain=d2gba02i1vlqqa.cloudfront.net`

2.アプリケーションのVersionファイルを用意する

標準のリリース方式に従えば勝手にVersionファイルは用意されるので、特別意識する必要はありません。
Versionファイルの内容はアプリ起動時にPlay.configurationに読み込まれます。

## カスタムタグ一覧 ##

### image ###

imageタグはHTMLのimgタグを挿入します。
imageタグは __動的参照タグ__ です。

    #{image file:'sample.jpg'}
    #{image file:'dir/sample.jpg', width:180, height:200, id:'sample'}

上記タグをHTMLに展開した結果は以下となります。

    <img src="/public/images/sample.jpg" />
    <img src="/public/images/dir/sample.jpg" width="180" height="200" id="sample" />

Playが本番モードで稼働している場合は以下のようにHTML展開されます。

    <img src="http://d2gba02i1vlqqa.cloudfront.net/20120331122431/images/sample.jpg"/>

本番モードのリソースの参照先は
`http://[cloudfrontドメイン]/[バージョン番号]/images/[ディレクトリ名]/[ファイル名]`
となります。

#### 必須属性 ####

<table>
  <tr>
    <td>属性名</td>
    <td>説明</td>
    <td>例</td>
  </tr>
  <tr>
    <td>file</td>
    <td>リソースのファイル名を指定します。指定したリソースはsrcとして展開されます。<br/>imagesの下からのパスを指定します。</td>
    <td>#{image file:'apple.jpg'}<br />#{image file:'parent/orange.jpg'}</td>
  </tr>
</table>

#### 任意属性 ####

<table>
  <tr>
    <td>属性名</td>
    <td>説明</td>
    <td>例</td>
  </tr>
  <tr>
    <td>alt</td>
    <td>altを出力します</td>
    <td>#{image file:'apple.jpg', alt:'りんご'}</td>
  </tr>
  <tr>
    <td>width</td>
    <td>widthを出力します</td>
    <td>#{image file:'apple.jpg', width:180}</td>
  </tr>
  <tr>
    <td>height</td>
    <td>heightを出力します</td>
    <td>#{image file:'apple.jpg', height:200}</td>
  </tr>
  <tr>
    <td>class</td>
    <td>classを出力します</td>
    <td>#{image file:'apple.jpg', class:'fruits'}</td>
  </tr>
  <tr>
    <td>dir</td>
    <td>dirを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>id</td>
    <td>idを出力します</td>
    <td>#{image file:'apple.jpg', id:'apple'}</td>
  </tr>
  <tr>
    <td>lang</td>
    <td>langを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>style</td>
    <td>styleを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>title</td>
    <td>titleを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>xml:lang</td>
    <td>xml:langを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onclick</td>
    <td>onclickを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>ondblclick</td>
    <td>ondblclickを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmousedown</td>
    <td>onmousedownを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmousemove</td>
    <td>onmousemoveを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmouseout</td>
    <td>onmouseoutを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmouseover</td>
    <td>onmouseoverを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmouseup</td>
    <td>onmouseupを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onkeydown</td>
    <td>onkeydownを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onkeypress</td>
    <td>onkeypressを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onkeyup</td>
    <td>onkeyupを出力します</td>
    <td></td>
  </tr>
</table>

***

### js ###

jsタグはHTMLのscriptタグを挿入します。jsタグは __動的参照タグ__ です。

    #{js file:'sample.js' /}
    #{js file:'dir/sample.js', charset:'utf-8' /}

上記タグをHTMLに展開した結果は以下となります。typeは自動的に埋め込まれます。
    
    <script type="text/javascript" src="/public/javascripts/sample.js" ></script>
    <script type="text/javascript" src="/public/javascripts/sample.js" charset="utf-8" ></script>


Playが本番モードで稼働している場合は以下のようにHTML展開されます。

    <script type="text/javascript" src="http://d2gba02i1vlqqa.cloudfront.net/20120331122431/javascripts/sample.js" ></script>


本番モードのリソースの参照先は
`http://[cloudfrontドメイン]/[バージョン番号]/javascripts/[ディレクトリ名]/[ファイル名]`
となります。

#### 必須属性 ####

<table>
  <tr>
    <td>属性名</td>
    <td>説明</td>
    <td>例</td>
  </tr>
  <tr>
    <td>file</td>
    <td>リソースのファイル名を指定します。指定したリソースはsrcとして展開されます。<br/>javascriptsの下からのパスを指定します。</td>
    <td>#{js file:'flect-common.js'}<br />#{js file:'parent/flect-common.js'}</td>
  </tr>
</table>

#### 任意属性 ####

<table>
  <tr>
    <td>属性名</td>
    <td>説明</td>
    <td>例</td>
  </tr>
  <tr>
    <td>charset</td>
    <td>charsetを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>defer</td>
    <td>deferを出力します</td>
    <td></td>
  </tr>
</table>

***

### css ###

cssタグはHTMLのlinkタグを挿入します。
cssタグは __動的参照タグ__ です。

    #{css file:'main.css' /}

上記タグをHTMLに展開した結果は以下となります。relとtypeは自動的に埋め込まれます。
    
	<link href="/public/stylesheets/main.css" rel="stylesheet" type="text/css" />

Playが本番モードで稼働している場合は以下のようにHTML展開されます。

    <link href="http://d2gba02i1vlqqa.cloudfront.net/20120331122431/stylesheets/main.css" rel="stylesheet" type="text/css" />

本番モードのリソースの参照先は
`http://[cloudfrontドメイン]/[バージョン番号]/stylesheets/[ディレクトリ名]/[ファイル名]`
となります。

#### 必須属性 ####

<table>
  <tr>
    <td>属性名</td>
    <td>説明</td>
    <td>例</td>
  </tr>
  <tr>
    <td>file</td>
    <td>リソースのファイル名を指定します。指定したリソースはhrefとして展開されます。<br/>stylesheetsの下からのパスを指定します。</td>
    <td>#{css file:'main.css'}<br />#{css file:'subdir/sub.css'}</td>
  </tr>
</table>

#### 任意属性 ####

<table>
  <tr>
    <td>属性名</td>
    <td>説明</td>
    <td>例</td>
  </tr>
  <tr>
    <td>charset</td>
    <td>charsetを出力します。</td>
    <td></td>
  </tr>
  <tr>
    <td>hreflang</td>
    <td>hreflangを出力します。</td>
    <td></td>
  </tr>
  <tr>
    <td>media</td>
    <td>media</td>
    <td></td>
  </tr>
  <tr>
    <td>rev</td>
    <td>revを出力します。</td>
    <td></td>
  </tr>
  <tr>
    <td>hreflang</td>
    <td>hreflangを出力します。</td>
    <td></td>
  </tr>
  <tr>
    <td>target</td>
    <td>targetを出力します。</td>
    <td></td>
  </tr>
    <tr>
    <td>class</td>
    <td>classを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>dir</td>
    <td>dirを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>id</td>
    <td>idを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>lang</td>
    <td>langを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>style</td>
    <td>styleを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>title</td>
    <td>titleを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>xml:lang</td>
    <td>xml:langを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onclick</td>
    <td>onclickを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>ondblclick</td>
    <td>ondblclickを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmousedown</td>
    <td>onmousedownを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmousemove</td>
    <td>onmousemoveを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmouseout</td>
    <td>onmouseoutを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmouseover</td>
    <td>onmouseoverを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onmouseup</td>
    <td>onmouseupを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onkeydown</td>
    <td>onkeydownを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onkeypress</td>
    <td>onkeypressを出力します</td>
    <td></td>
  </tr>
  <tr>
    <td>onkeyup</td>
    <td>onkeyupを出力します</td>
    <td></td>
  </tr>
</table>
