# Article Service Codex

## 1. 概述
本服务负责文章的增删改查。

## 2. 接口需求 (Frontend Requirements)
为了支持前端微信公众号风格的展示，需要以下字段支持：

### 2.1 文章模型 (Article Model)
- **cover_image** (String): 文章封面图 URL。建议比例 16:9。
- **summary** (String): 文章摘要，用于列表页展示。
- **view_count** (Integer): 阅读量。
- **like_count** (Integer): 点赞数。
- **wow_count** (Integer): 在看数。

### 2.2 接口调整
- `GET /api/article/public/list`: 返回列表需包含 `cover_image`。
- `GET /api/article/public/{id}`: 返回详情需包含统计数据 (`view_count`, etc.)。

## 3. 数据库变更 (Database Changes)
请执行以下 SQL 脚本以更新数据库结构：

```sql
-- 2025-12-02: Add cover_image and wow_count
ALTER TABLE blog_article ADD COLUMN cover_image VARCHAR(255) DEFAULT NULL COMMENT '文章封面图';
ALTER TABLE blog_article ADD COLUMN wow_count BIGINT DEFAULT 0 COMMENT '在看数';
```
