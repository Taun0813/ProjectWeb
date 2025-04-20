
# ⚙️ Backend - E-Commerce Microservices

Đây là phần backend của hệ thống thương mại điện tử, được xây dựng theo kiến trúc **Microservices** sử dụng Spring Boot, Kafka, PostgreSQL và Eureka.

---

## 🧱 Kiến trúc hệ thống

```
BE/
├── api-gateway/               # Cổng giao tiếp chính, routing đến các service
├── eureka-server/             # Service Discovery - đăng ký và tìm kiếm service
├── user-service/              # Quản lý người dùng
├── product-service/           # Quản lý sản phẩm
├── order-service/             # Quản lý đơn hàng
├── notification-service/      # Gửi thông báo dựa trên Kafka
├── docker-compose.yml         # Cấu hình chạy toàn bộ hệ thống bằng Docker
└── ...
```

---

## 🧪 Công nghệ sử dụng

- Java 17 + Spring Boot
- Spring Cloud Gateway
- Spring Cloud Eureka
- Spring Data JPA (PostgreSQL)
- Apache Kafka
- Spring Security
- Docker & Docker Compose

---

## 🚀 Hướng dẫn chạy

### ⚙️ Yêu cầu hệ thống

- Java 17
- Maven
- Docker + Docker Compose

---

### ▶️ Chạy bằng Docker Compose

```bash
cd BE
docker-compose up --build
```

> Dịch vụ sẽ khởi chạy như sau:
> - ✅ Eureka: `http://localhost:8761`
> - ✅ API Gateway: `http://localhost:8080`
> - ✅ Kafka: `localhost:9092`
> - ✅ PostgreSQL: `localhost:5432`

---

### ▶️ Chạy thủ công từng service (tuỳ chọn)

```bash
cd eureka-server
mvn spring-boot:run

cd api-gateway
mvn spring-boot:run

# Lặp lại với các service khác
```

> Bạn cần đảm bảo các biến môi trường và database đã cấu hình đúng.

---

## 🌐 Giao tiếp và Kafka Flow

- Mọi request đi qua `api-gateway`
- Các service đăng ký qua `eureka-server`
- `order-service` gửi sự kiện qua Kafka topic
- `notification-service` lắng nghe Kafka và xử lý

```text
[order-service] ---> Kafka Topic "order-events" ---> [notification-service]
```

---

## 🔐 Bảo mật

- Dự kiến sử dụng JWT và Spring Security
- Các API có thể phân quyền theo vai trò trong tương lai

---

## 🛠️ Đang phát triển

- [ ] Thêm email service, gửi mail thật
- [ ] Logging + Zipkin/ELK cho tracking
- [ ] CI/CD workflow

---

## 🧑‍💻 Tác giả

- 👤 **Taun0813** - [GitHub](https://github.com/Taun0813)

---

## 📜 License

Dự án được phát triển với mục đích học tập và phi thương mại.
