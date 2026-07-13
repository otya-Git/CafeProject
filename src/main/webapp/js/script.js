document.addEventListener('DOMContentLoaded', function() {
    const hamburger = document.getElementById('js-hamburger');
    const nav = document.getElementById('js-nav');

    // パディングを含めて高さを計算し、画面内に収めてスクロール可能にする
    nav.style.boxSizing = 'border-box';
    nav.style.height = '100vh';
    nav.style.maxHeight = '100vh';
    nav.style.overflowY = 'auto';

    hamburger.addEventListener('click', function() {
        hamburger.classList.toggle('active');
        nav.classList.toggle('active');

        if (nav.classList.contains('active')) {
            // ▼【ガタつき防止】スクロールバーが消える分の「ガタッ」という動きを相殺する
            const scrollbarWidth = window.innerWidth - document.documentElement.clientWidth;
            document.body.style.paddingRight = `${scrollbarWidth}px`;
            
            // 後ろのページを固定
            document.body.style.overflow = 'hidden';
        } else {
            // 【元の状態に戻す】
            document.body.style.paddingRight = '';
            document.body.style.overflow = '';
        }
    });
});
