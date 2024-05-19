// 필터링 처리 함수
function applyFilters() {
    const locationFilter = document.getElementById('location').value;
    const categoryFilter = document.getElementById('category').value;
    const priceFilter = document.getElementById('price').value;
    const dateFilter = document.getElementById('date').value;
  
    // 모든 상품 카드 요소 가져오기
    const productCards = document.querySelectorAll('.card');
  
    // 각 상품 카드에 대해 필터링 조건을 적용
    productCards.forEach(card => {
      const cardLocation = card.dataset.location.toLowerCase();
      const cardCategory = card.dataset.category.toLowerCase();
      const cardPrice = parseFloat(card.dataset.price.replace(',', ''));
      const cardDate = card.dataset.date;
  
      // 필터링 조건 확인
      const locationMatch = locationFilter === 'all' || cardLocation.includes(locationFilter);
      const categoryMatch = categoryFilter === 'all' || cardCategory === categoryFilter.toLowerCase();
      const priceMatch = priceFilter === 'all' ||
        (priceFilter === '0-500000' && cardPrice >= 0 && cardPrice <= 50000) ||
        (priceFilter === '50000-100000' && cardPrice > 50000 && cardPrice <= 100000) ||
        (priceFilter === '100000-3000000' && cardPrice > 100000 && cardPrice <= 3000000) ||
        (priceFilter === '3000000' && cardPrice > 3000000);
      const dateMatch = dateFilter === '' || cardDate === dateFilter;
  
      // 조건에 맞는 상품 카드는 보여주고, 맞지 않는 카드는 숨김 처리
      if (locationMatch && categoryMatch && priceMatch && dateMatch) {
        card.style.display = 'block';
      } else {
        card.style.display = 'none';
      }
    });
  }
  
  // 검색 버튼 클릭 이벤트 처리
  document.addEventListener('DOMContentLoaded', function () {
    const applyFilterButton = document.getElementById('apply-filter');
    if (applyFilterButton) {
      applyFilterButton.addEventListener('click', function () {
        applyFilters();
      });
    }
  });
  