// меню
document.getElementById("menu-toggle").addEventListener("click", function () {
    const menu = document.getElementById("menu");
    menu.style.display = (menu.style.display === "block") ? "none" : "block";
});

// слайдер
let current = 0;
const slides = document.querySelectorAll(".slide");

function showSlide(index) {
    slides.forEach(s => s.classList.remove("active"));
    slides[index].classList.add("active");
}

document.getElementById("next").addEventListener("click", () => {
    current = (current + 1) % slides.length;
    showSlide(current);
});

document.getElementById("prev").addEventListener("click", () => {
    current = (current - 1 + slides.length) % slides.length;
    showSlide(current);
});

// форма
document.getElementById("contact-form").addEventListener("submit", function(e) {
    e.preventDefault();
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const message = document.getElementById("message").value.trim();
    const msg = document.getElementById("form-msg");

    if (name.length < 2) {
        msg.textContent = "Имя слишком короткое!";
        msg.style.color = "red";
        return;
    }
    if (!email.includes("@")) {
        msg.textContent = "Некорректный email!";
        msg.style.color = "red";
        return;
    }
    if (message.length < 5) {
        msg.textContent = "Сообщение слишком короткое!";
        msg.style.color = "red";
        return;
    }

    msg.textContent = "Сообщение успешно отправлено!";
    msg.style.color = "green";
});

// тема
document.getElementById("theme-toggle").addEventListener("click", () => {
    document.body.classList.toggle("dark");
});