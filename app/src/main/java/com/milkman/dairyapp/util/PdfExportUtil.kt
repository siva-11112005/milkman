package com.milkman.dairyapp.util

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import com.milkman.dairyapp.data.model.MonthlyCustomerReport

object PdfExportUtil {
    fun exportMonthlyReport(
        context: Context,
        uri: Uri,
        month: String,
        reportRows: List<MonthlyCustomerReport>,
        includeAmount: Boolean
    ) {
        val document = PdfDocument()
        val paint = Paint()
        paint.textSize = 12f

        var pageNumber = 1
        var pageInfo = PdfDocument.PageInfo.Builder(595, 842, pageNumber).create()
        var page = document.startPage(pageInfo)
        var canvas = page.canvas

        var y = 40
        val left = 30

        fun newPage() {
            document.finishPage(page)
            pageNumber += 1
            pageInfo = PdfDocument.PageInfo.Builder(595, 842, pageNumber).create()
            page = document.startPage(pageInfo)
            canvas = page.canvas
            y = 40
        }

        canvas.drawText("Milkman Monthly Report - $month", left.toFloat(), y.toFloat(), paint)
        y += 24

        var totalQty = 0.0
        var totalAmount = 0.0

        reportRows.forEachIndexed { index, row ->
            if (y > 800) newPage()
            val baseText = "${index + 1}. ${row.customerName} (${row.customerType}) - ${"%.2f".format(row.totalQuantity)} L"
            canvas.drawText(baseText, left.toFloat(), y.toFloat(), paint)
            y += 18
            if (includeAmount) {
                canvas.drawText("    Amount: ${"%.2f".format(row.totalAmount)}", left.toFloat(), y.toFloat(), paint)
                y += 18
            }
            totalQty += row.totalQuantity
            totalAmount += row.totalAmount
        }

        if (y > 800) newPage()
        y += 8
        canvas.drawText("Total Quantity: ${"%.2f".format(totalQty)} L", left.toFloat(), y.toFloat(), paint)
        y += 18
        if (includeAmount) {
            canvas.drawText("Total Amount: ${"%.2f".format(totalAmount)}", left.toFloat(), y.toFloat(), paint)
        }

        document.finishPage(page)

        context.contentResolver.openOutputStream(uri)?.use { output ->
            document.writeTo(output)
        }

        document.close()
    }
}
