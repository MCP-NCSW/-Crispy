document.addEventListener("DOMContentLoaded", function() {
    const eventSource = new EventSource(`/notifications/${currentEmpNo}`);

    const notificationCountElement = document.getElementById("notification-count");
    const notificationListElement = document.getElementById("notification-list");

    function updateNotificationCount(count) {
        notificationCountElement.textContent = count;
    }

    function addNotificationMessage(message) {
        console.log(message);
        const li = document.createElement("li");
        li.className = 'my-menu-item';
        li.innerHTML = `<span>${message}</span>`;
        notificationListElement.prepend(li); // 새 알림을 목록 상단에 추가
    }

    fetch(`/api/notifications/unreadCount/${currentEmpNo}`)
        .then(response => response.json())
        .then(data => {
            updateNotificationCount(data.count);
        })
        .catch(error => console.error('Error fetching unread notification count:', error));

    fetch(`/api/notifications/unread/${currentEmpNo}`)
        .then(response => response.json())
        .then(data => {
            data.forEach(notification => {
                addNotificationMessage(notification.notifyContent);
            });
        })
        .catch(error => console.error('Error fetching unread notifications:', error));


    eventSource.addEventListener('notification', function(event) {
        console.log(event);
        const data = event.data
        alert(`새로운 알림: ${data}`);
        let currentCount = parseInt(notificationCountElement.textContent, 10);
        updateNotificationCount(currentCount + 1);
        addNotificationMessage(data);
    });

    eventSource.onerror = function(event) {
        console.error("SSE connection error:", event);
        eventSource.close();
    };
});