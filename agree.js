document.addEventListener('DOMContentLoaded', function() {
    const checkAll = document.getElementById('checkAll');
    const subChecks = document.querySelectorAll('.sub-check');

    // 1. 전체 체크박스 클릭 시 모든 하위 체크박스 제어
    checkAll.addEventListener('change', function() {
        subChecks.forEach(check => {
            check.checked = checkAll.checked;
        });
    });

    // 2. 하위 체크박스 개별 클릭 시 전체 체크박스 상태 업데이트
    subChecks.forEach(check => {
        check.addEventListener('change', function() {
            const checkedCount = document.querySelectorAll('.sub-check:checked').length;
            checkAll.checked = (checkedCount === subChecks.length);
        });
    });
});
