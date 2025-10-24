# 🔧 Configuración del archivo .env

## 📋 Instrucciones de configuración:

### 1. **Copiar el archivo de ejemplo:**
```bash
copy .env.example .env
```

### 2. **Editar el archivo `.env` con tus credenciales:**

#### 📧 **Email (OBLIGATORIO para funcionalidad de email):**
```env
SPRING_MAIL_USERNAME=tu_correo_real@gmail.com
SPRING_MAIL_PASSWORD=tu_contraseña_de_aplicacion_real
```

#### 🔐 **Base de datos (opcional - cambiar si lo deseas):**
```env
POSTGRES_USER=tu_usuario_personalizado
POSTGRES_PASSWORD=tu_contraseña_segura
```

#### 🐰 **RabbitMQ (opcional - cambiar si lo deseas):**
```env
RABBITMQ_DEFAULT_USER=tu_usuario_rabbitmq
RABBITMQ_DEFAULT_PASS=tu_contraseña_rabbitmq
```

### 3. **Ejecutar el proyecto:**
```bash
docker-compose up --build -d
```

## ⚠️ **Importante:**
- **NUNCA** subas el archivo `.env` al repositorio (ya está en `.gitignore`)
- **SÍ** sube el archivo `.env.example` como referencia para otros desarrolladores
- Usa **contraseñas de aplicación** para Gmail, no tu contraseña personal

## 🔗 **URLs de acceso:**
- Aplicación: http://localhost:8080
- RabbitMQ Management: http://localhost:15672
- PostgreSQL: localhost:5432