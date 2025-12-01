import http, { ARTICLE_SERVICE_BASE } from './http';

export const fetchPublicArticles = () => {
    return http.get(`${ARTICLE_SERVICE_BASE}/public/list`);
};

export const fetchArticleDetailPublic = (id: string) => {
    return http.get(`${ARTICLE_SERVICE_BASE}/public/${id}`);
};

export const fetchArticleDetailPrivate = (id: string) => {
    return http.get(`${ARTICLE_SERVICE_BASE}/${id}`);
};

export const createArticle = (payload: any) => {
    return http.post(`${ARTICLE_SERVICE_BASE}`, payload);
};
