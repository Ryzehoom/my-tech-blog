# User Service Codex

## 1. 概述
本服务负责用户认证和用户信息管理。

## 2. 接口需求 (Frontend Requirements)
为了支持前端展示作者信息，需要以下字段支持：

### 2.1 用户模型 (User Model)
- **avatar** (String): 用户头像 URL。
- **nickname** (String): 用户昵称（已存在，确认返回）。
- **signature** (String): 个性签名（可选，用于主页展示）。

### 2.2 接口调整
- `POST /api/user/login`: 返回的 User 信息中需包含 `avatar`。

## 3. 数据库变更 (Database Changes)
请执行以下 SQL 脚本以更新数据库结构：

```sql
-- 2025-12-02: Add avatar and signature
ALTER TABLE blog_user ADD COLUMN avatar VARCHAR(255) DEFAULT NULL COMMENT '用户头像';
ALTER TABLE blog_user ADD COLUMN signature VARCHAR(255) DEFAULT NULL COMMENT '个性签名';
```
