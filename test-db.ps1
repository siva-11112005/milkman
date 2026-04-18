# Step 1: Login to get token
$loginResponse = Invoke-WebRequest -Uri 'http://localhost:8080/api/auth/login' -Method POST -ContentType 'application/json' -Body '{"username":"admin","password":"admin123"}' -UseBasicParsing
$loginData = $loginResponse.Content | ConvertFrom-Json
$token = $loginData.token

Write-Host "✅ Login successful" -ForegroundColor Green
Write-Host "Token received"

# Step 2: Test diagnostic endpoint to see MongoDB error
Write-Host "`n🧪 Testing MongoDB write with diagnostic endpoint..." -ForegroundColor Yellow
$testResponse = Invoke-WebRequest -Uri 'http://localhost:8080/api/test/create-customer' -Method POST -ContentType 'application/json' -Headers @{'Authorization'="Bearer $token"} -UseBasicParsing
$testData = $testResponse.Content | ConvertFrom-Json

Write-Host "`n📊 Diagnostic Result:" -ForegroundColor Cyan
if ($testData.success) {
    Write-Host "✅ MongoDB write WORKS!" -ForegroundColor Green
    Write-Host "Customer ID: $($testData.data._id)"
} else {
    Write-Host "❌ MongoDB write FAILED!" -ForegroundColor Red
    Write-Host "Error: $($testData.error)"
    Write-Host "Error Code: $($testData.code)"
    Write-Host "Stack: $($testData.stack)"
}
