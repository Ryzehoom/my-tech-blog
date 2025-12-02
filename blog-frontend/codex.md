# Blog Frontend Codex

## 1. 项目概述
本项目是一个基于 Vue 3 + Vite + TypeScript + Tailwind CSS 的个人技术博客前端。
设计风格模仿微信公众号文章阅读体验（WeChat Official Account Style）。

## 2. 技术栈
- **核心框架**: Vue 3 (Script Setup)
- **构建工具**: Vite
- **语言**: TypeScript
- **样式**: Tailwind CSS
- **路由**: Vue Router
- **状态管理**: Pinia
- **HTTP客户端**: Axios

## 3. 目录结构
```
src/
  api/          # API 接口封装
  assets/       # 静态资源
  components/   # 公共组件
  router/       # 路由配置
  stores/       # Pinia 状态管理
  views/        # 页面组件
  App.vue       # 根组件
  main.ts       # 入口文件
  style.css     # 全局样式
```

## 4. 样式指南 (WeChat Style)
### 4.1 全局设置
- **背景色**: `#f6f6f6` (浅灰)
- **主文字色**: `#333333` (深灰)
- **字体**: 系统默认无衬线字体 (San Francisco, Roboto, etc.)

### 4.2 文章详情页 (`ArticleDetail.vue`)
- **容器宽度**: 最大 `677px` (模拟手机/平板阅读舒适区)
- **标题**: `22px` - `24px`, 加粗, margin-bottom `16px`
- **元信息**: `15px`, 灰色/淡化, 包含 "My Tech Blog", 作者, 时间
- **正文**:
  - 字号: `17px`
  - 行高: `1.6`
  - 颜色: `#333`
  - 对齐: 两端对齐 (`text-justify`)
- **互动区**: 底部点赞/在看 (模拟)

### 4.3 文章列表页 (`ArticleList.vue`)
- **卡片风格**: 白色背景, 圆角, 阴影
- **封面图**: 16:9 比例 (目前使用占位符)
- **标题**: 图片底部渐变遮罩 + 白色文字

## 5. 接口交互
- **Base URL**: `/user-api` (User Service), `/article-api` (Article Service)
- **Authentication**: Bearer Token via `Authorization` header

## 6. 待办事项 / 需求池
- [x] 接入真实封面图 (后端已支持 `cover_image`)
- [x] 接入作者头像 (后端已支持 `avatar`)
- [ ] 完善点赞/在看交互逻辑
