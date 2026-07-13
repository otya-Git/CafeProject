<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<!-- シフト画面専用のCSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shift.css">

<div class="shift-container">
    <h2>📅 シフト一覧（閲覧用）</h2>
    
    <!-- 今月の表示（例：2026年 7月） -->
    <div class="month-title">
        <h3>${currentYear}年 ${currentMonth}月</h3>
    </div>

    <!-- カレンダー風のテーブル -->
    <table class="calendar-table">
        <thead>
            <tr>
                <th class="sun">日</th>
                <th>月</th>
                <th>火</th>
                <th>水</th>
                <th>木</th>
                <th>金</th>
                <th class="sat">土</th>
            </tr>
        </thead>
        <tbody>
            <!-- カレンダーの行（週）をループ -->
            <c:forEach var="week" items="${calendarWeeks}">
                <tr>
                    <!-- 各曜日（7日間）をループ -->
                    <c:forEach var="day" items="${week}">
                        <!-- 💡 修正箇所：day.isCurrentMonth から day.currentMonth に変更 -->
                        <!-- これによりJava側の isCurrentMonth() ゲッターが正しく呼び出されます -->
                        <td class="${day.currentMonth ? '' : 'other-month'}">
                            
                            <!-- 日付の数字 -->
                            <div class="day-number">${day.dayOfMonth}</div>
                            
                            <!-- その日のシフト情報（複数人いる場合は縦に並ぶ） -->
                            <div class="shift-info-area">
                                <c:forEach var="shift" items="${day.shifts}">
                                    <div class="shift-badge">
                                        <span class="staff-name">${shift.staffName}</span>
                                        <span class="shift-time">${shift.startTime}〜${shift.endTime}</span>
                                    </div>
                                </c:forEach>
                            </div>
                            
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="../footer.jsp"%>
