# 通知我 - PushMe 通知推送插件

基于 Halo 2.25+ 的通知推送插件，通过 PushMe 服务将博客动态推送到手机。

## 功能

- 新评论通知
- 评论审核通知
- 文章发布通知
- 投稿审核通知
- 文章删除通知
- 支持 PushMe 多设备推送（apiKey 逗号分隔）
- 支持自定义推送接口（模板变量）

## 配置

### 推送接口地址

推送 URL 模板，支持以下变量：

| 变量 | 说明 |
|------|------|
| `{apiKey}` | API 密钥（多个用逗号分隔） |
| `{title}` | 通知标题 |
| `{content}` | 通知内容 |
| `{type}` | 消息类型（固定 markdown） |
| `{extValue}` | 扩展值，可用于自定义参数 |

示例：

```
http://your-pushme-server:3010/?push_key={apiKey}&title={title}&content={content}&type={type}
```

留空则不推送。

### 基本设置

| 字段 | 说明 |
|------|------|
| API密钥 | PushMe 管理后台生成的密钥 |
| 扩展值 | 可选，自定义推送参数，在 URL 中用 `{extValue}` 引用 |
| 站点地址 | 博客访问地址，尾部不要带斜杠 |

### 通知开关

- 通知自己的操作：勾选后管理员/作者自己的操作也推送
- 新评论、评论审核、文章发布、投稿审核、文章删除：分别控制各事件是否推送

## 安装

1. 从 [Releases](https://github.com/cnfniw/notify-me/releases) 下载最新 JAR
2. Halo 后台 → 插件管理 → 安装 → 上传 JAR
3. 启用插件后进入设置页配置

## 开发

```bash
# 克隆
git clone https://github.com/cnfniw/notify-me.git

# 构建
cd notify-me
./gradlew build -x test

# JAR 在 build/libs/notifyme-*.jar
```

## 依赖

- Halo >= 2.25.0
- [PushMe Server](https://github.com/yafoo/pushme-server)

## License

GPL-3.0
