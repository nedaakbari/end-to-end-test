# end-to-end-test
مهم ترین نکاتی که توی نوشتن تست اتومات باید بهش توجه کرد چیه ؟

نوشتن تست اتومات (Unit Test، Integration Test و …) فقط کدنویسی ساده نیست؛ اصول و نکات مهمی داره که اگه رعایت بشه تست‌ها هم قابل اعتماد میشن و هم نگهداریشون راحت‌تر.

مهم‌ترین نکات:

✅ اصول کلی

قابل تکرار بودن (Repeatability)
تست باید هر بار با نتیجه‌ی یکسان اجرا بشه؛ وابستگی به محیط بیرونی (زمان سیستم، فایل‌های موقت، اینترنت و …) باعث flaky test میشه.

ایزوله بودن (Isolation)
هر تست مستقل از تست‌های دیگه باشه. شکست یا موفقیت یک تست نباید روی تست بعدی تأثیر بذاره.

سریع بودن (Fast Feedback)
تست‌ها باید سریع اجرا بشن تا بتونی مرتب CI/CD یا حتی local اجرا کنی.

خوانایی و وضوح (Readability)
کدی که برای تست می‌نویسی باید مثل مستندات باشه. Developer بعدی باید راحت بفهمه سناریو چی بوده.

AAA Pattern (Arrange-Act-Assert)
تست رو همیشه به سه بخش تقسیم کن:

Arrange → آماده‌سازی داده‌ها و mockها

Act → اجرای عملیاتی که می‌خوای تست کنی

Assert → بررسی نتیجه

✅ پوشش و کیفیت تست

تمرکز روی Behavior نه Implementation
تست باید رفتار (خروجی و اثر) رو بررسی کنه نه جزئیات پیاده‌سازی که ممکنه تغییر کنه.

حد معقول پوشش کد (Code Coverage)
پوشش بالا خوبه، ولی مهم‌تر از درصد پوشش، کیفیت سناریوهای تست شده‌ست.

سناریوهای مثبت و منفی

Positive case: ورودی درست → نتیجه درست

Negative case: ورودی اشتباه → خطا یا رفتار کنترل‌شده

Boundary Testing (لبه‌ها)
مثلا وقتی list خالیه، یا وقتی فقط یک عنصر داره، یا وقتی به حد نهایی ظرفیت می‌رسه.

✅ نگهداری و طراحی

DRY در تست
تکرار زیاد کد در تست‌ها باعث سختی نگهداری میشه → از helper method، fixture یا factory استفاده کن.

Mock و Stub درست

از Mock برای شبیه‌سازی رفتار وابستگی‌ها (DB، API، …) استفاده کن.

ولی زیاده‌روی نکن چون تستت از واقعیت دور میشه.

Integration Test vs Unit Test
فقط Unit Test کافی نیست؛ باید تست یکپارچه‌سازی هم باشه که مطمئن شی سرویس‌ها واقعا با هم درست کار می‌کنن.

Stable Data
داده‌ی تستی باید مشخص و پایدار باشه؛ تغییر نکنه یا وابسته به دیتابیس واقعی نباشه.

Automation در CI/CD
تست‌ها باید بخشی از pipeline باشن، نه اینکه فقط دستی اجرا بشن.

#####
یک چک‌لیست عملی برای تست اتومات که می‌تونی قبل از commit یا توی CI/CD مرور کنی:#
📝 چک‌لیست تست اتومات
📦 طراحی تست

 تست‌هام از AAA pattern (Arrange-Act-Assert) پیروی می‌کنن

 هر تست فقط یک سناریو رو بررسی می‌کنه (Single Responsibility)

 اسم تست‌هام واضح و گویا هست (مثلاً: shouldReturnError_whenUserNotFound)

⚡ اجرا

 تست‌هام سریع اجرا می‌شن (زیر چند ثانیه)

 تست‌ها ایزوله هستن و به دیتابیس/شبکه واقعی وابسته نیستن (مگر توی Integration Test)

 هیچ تستی Flaky (نتیجه متغیر در هر بار اجرا) نیست

✅ پوشش و سناریو

 برای هر متد، هم positive case دارم هم negative case

 شرایط Boundary رو تست کردم (مقادیر صفر، null، لیست خالی، حد نهایی، …)

 Code coverage معقول دارم (نه صرفاً درصد بالا، بلکه سناریوهای مهم)

🔧 نگهداری

 از helper / factory برای تولید داده تستی استفاده کردم، کد تکراری ندارم

 از mock/stub فقط در صورت نیاز استفاده کردم (نه زیاده‌روی)

 داده تستی من stable و ثابت هست (با هر بار اجرا تغییر نمی‌کنه)

🚀 اتوماسیون

 تست‌ها به صورت خودکار در CI/CD pipeline اجرا می‌شن

 در صورت شکست تست، build fail می‌شه

 نتایج تست‌ها لاگ و گزارش‌گیری می‌شن (JUnit report، Allure و …)
 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 Stack پیشنهادی E2E API Testing
1️⃣ HTTP Client & Assertion

Rest Assured

برای ارسال HTTP Request و assert روی Response

پشتیبانی از JSON, XML, Status Code, Headers

امکان chain کردن callها (workflow کاربر)

2️⃣ Database Management

Testcontainers

بالا آوردن دیتابیس واقعی داخل Docker به صورت ایزوله و temporary

هر بار تست یک instance تازه میاره → repeatable و isolated

مناسب برای PostgreSQL, MySQL, MongoDB و حتی Kafka/RabbitMQ

Flyway / Liquibase (اختیاری)

برای migrate کردن schema دیتابیس قبل از تست

3️⃣ Async / Wait Handling

Awaitility

برای انتظار شرطی (polling) به جای Thread.sleep()

مناسب وقتی backend async یا queue-based هست

4️⃣ Test Framework

JUnit 5 / TestNG

مدیریت lifecycle تست‌ها (before/after each test, before/after all)

اجرای گروهی تست‌ها و assertion بیشتر

5️⃣ Reporting

Allure / Extent Reports

تولید گزارش زیبا با جزئیات request/response

قابل integration با CI/CD

6️⃣ CI/CD Integration

Jenkins / GitHub Actions / GitLab CI

اجرای خودکار E2E تست‌ها در هر commit

Fail کردن build در صورت شکست تست‌ها

امکان لاگ و گزارش‌گیری

7️⃣ Optional

WireMock / MockServer

شبیه‌سازی سرویس‌های خارجی در صورت نیاز

مفید برای dependencyهایی که دسترسی real ندارن یا پرهزینه هستن

🔹 Workflow پیشنهادی

CI/CD بالا میاد → Dockerized test environment شامل دیتابیس و سرویس‌ها

داده‌های تستی با Flyway / seed scripts آماده میشن

E2E تست‌ها با Rest Assured اجرا میشن

Awaitility برای انتظار async و queueها استفاده میشه

تست‌ها تمام میشن → دیتابیس و کانتینرها پاک میشن

گزارش‌ها توسط Allure تولید و ضمیمه میشن

Build succeed/fail بر اساس نتیجه تست‌ها