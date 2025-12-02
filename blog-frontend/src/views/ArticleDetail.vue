<template>
  <div class="bg-white min-h-screen">
    <div class="max-w-[677px] mx-auto px-4 py-8 md:py-12">
      <div v-if="loading" class="text-center text-gray-400 py-12">
        Loading article...
      </div>
      
      <div v-else-if="error" class="text-center text-red-400 py-12">
        {{ error }}
      </div>
      
      <article v-else-if="article" class="font-sans">
        <h1 class="text-[22px] md:text-[24px] font-bold text-[#333] leading-[1.4] mb-4 tracking-wide">
          {{ article.title }}
        </h1>
        
        <div class="flex items-center space-x-4 text-[15px] text-[rgba(0,0,0,0.3)] mb-8">
          <span class="text-[#576b95] font-medium cursor-pointer">My Tech Blog</span>
          <span v-if="article.authorName">{{ article.authorName }}</span>
          <span>{{ formatDate(article.createTime) }}</span>
        </div>
        
        <!-- Cover Image (Optional, if we had one) -->
        <!-- <img v-if="article.cover" :src="article.cover" class="w-full rounded-lg mb-8" /> -->

        <div class="prose prose-lg max-w-none">
          <div class="whitespace-pre-wrap text-[17px] text-[#333] leading-[1.6] tracking-wide text-justify break-words">
            {{ article.content }}
          </div>
        </div>
        
        <div class="mt-12 pt-8 border-t border-gray-100 text-[14px] text-gray-400 flex justify-between items-center">
          <span>Views 100k+</span>
          <div class="flex space-x-4">
             <span>Like {{ article.likeCount || 0 }}</span>
             <span>Wow {{ article.wowCount || 0 }}</span>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { fetchArticleDetailPublic, fetchArticleDetailPrivate } from '../api/article';
import { useAuthStore } from '../stores/auth';

const route = useRoute();
const authStore = useAuthStore();
const article = ref<any>(null);
const loading = ref(true);
const error = ref('');

const formatDate = (dateStr: string) => {
  if (!dateStr) return '';
  return new Date(dateStr).toLocaleDateString();
};

onMounted(async () => {
  const id = route.params.id as string;
  if (!id) return;

  try {
    // If logged in, maybe try private endpoint? Or just stick to public for now unless it's a private article.
    // The requirement says: "ArticleDetail.vue: ... call public detail or private detail"
    // We'll try public first, or decide based on some flag. For now, let's just try public.
    // Actually, if the user is the author or if it's a draft, we might need private.
    // Let's assume public for viewing.
    
    let response;
    try {
        response = await fetchArticleDetailPublic(id);
    } catch (e) {
        // If public fails (maybe 403/404), and we are logged in, try private
        if (authStore.token) {
             response = await fetchArticleDetailPrivate(id);
        } else {
            throw e;
        }
    }
    
    article.value = response.data.data || response.data;
  } catch (err) {
    console.error(err);
    error.value = 'Failed to load article.';
  } finally {
    loading.value = false;
  }
});
</script>
