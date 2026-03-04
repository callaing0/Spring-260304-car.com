// 페이지가 로드된 후 실행
document.addEventListener('DOMContentLoaded', function() {
    
    // 회원가입 폼이 있는지 확인
    const signupForm = document.querySelector('form[action="/signup"]');
    
    if (signupForm) {
        signupForm.addEventListener('submit', function(event) {
            const password = document.getElementById('password').value;
            const passwordConfirm = document.getElementById('password-confirm').value;

            // 비밀번호 일치 여부 검사
            if (password !== passwordConfirm) {
                alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
                event.preventDefault(); // 서버로 폼이 제출되는 것을 막음
            }
        });
    }
});
