import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '../stores/auth';
import ArticleList from '../views/ArticleList.vue';
import Login from '../views/Login.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: ArticleList
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/articles',
            redirect: '/'
        },
        {
            path: '/articles/:id',
            name: 'article-detail',
            component: () => import('../views/ArticleDetail.vue')
        },
        {
            path: '/dashboard',
            component: () => import('../views/DashboardLayout.vue'),
            meta: { requiresAuth: true },
            children: [
                {
                    path: 'articles/new',
                    name: 'new-article',
                    component: () => import('../views/ArticleEditor.vue')
                }
            ]
        }
    ]
});

router.beforeEach((to, _from, next) => {
    const authStore = useAuthStore();

    if (to.meta.requiresAuth && !authStore.token) {
        next('/login');
    } else {
        next();
    }
});

export default router;
