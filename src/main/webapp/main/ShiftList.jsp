<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shift.css">

<div class="shift-container">
    <h2>シフト一覧</h2>
    
    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <!-- 💡 ボタンの幅に綺麗にフィットする、横幅コンパクトなデザインに変更しました a-->
        <div class="admin-action-box" style="padding: 15px 20px; margin-bottom: 25px; background-color: #f5efe6; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.02); width: max-content;">
            <div style="display: flex; align-items: center; gap: 15px;">
                <div>
                    <h3 style="margin: 0; color: #4a3c31; font-size: 1rem; font-weight: bold;">管理者メニュー</h3>
                    <p style="margin: 2px 0 0 0; color: #7a6e65; font-size: 0.8rem;">管理者権限専用エリア</p>
                </div>
                <a href="${pageContext.request.contextPath}/ShiftInsertServlet" class="btn-edit" style="display: inline-block; padding: 8px 16px; background-color: #5c4d43; color: white; text-decoration: none; border-radius: 6px; font-weight: bold; font-size: 0.85rem; transition: background-color 0.2s; box-shadow: 0 2px 4px rgba(0,0,0,0.1); white-space: nowrap;">
                    ＋ 新規シフトを追加する
                </a>
            </div>
        </div>
    </c:if>

    
     <!-- 💡 年月の表示と、前月・翌月への切り替えボタンを追加しました -->
    <div class="month-selector-area" style="display: flex; justify-content: center; align-items: center; gap: 20px; margin-bottom: 20px;">
        
        <%
            // 現在表示している年月から、前月と翌月の数値を計算します
            int cYear = (Integer)request.getAttribute("currentYear");
            int cMonth = (Integer)request.getAttribute("currentMonth");
            
            int prevYear = cYear;
            int prevMonth = cMonth - 1;
            if (prevMonth < 1) {
                prevMonth = 12;
                prevYear--;
            }
            
            int nextYear = cYear;
            int nextMonth = cMonth + 1;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
            
            request.setAttribute("prevYear", prevYear);
            request.setAttribute("prevMonth", prevMonth);
            request.setAttribute("nextYear", nextYear);
            request.setAttribute("nextMonth", nextMonth);
        %>

        <!-- ⬅️ 前月へボタン -->
        <a href="${pageContext.request.contextPath}/ShiftServlet?year=${prevYear}&month=${prevMonth}" 
           style="text-decoration: none; color: #5c4d43; font-weight: bold; padding: 6px 12px; background-color: #f5efe6; border-radius: 4px; font-size: 0.9rem; box-shadow: 0 1px 3px rgba(0,0,0,0.05);">
            ◀ 前月
        </a>

        <!-- 年月表示（中央） -->
        <h3 style="color: #4a3c31; font-weight: bold; font-size: 1.2rem; margin: 0; min-width: 120://0px; text-align: center; letter-spacing: 1px;">
            ${currentYear}年 ${currentMonth}月
        </h3>

        <!-- ➡️ 翌月へボタン -->
        <a href="${pageContext.request.contextPath}/ShiftServlet?year=${nextYear}&month=${nextMonth}" 
           style="text-decoration: none; color: #5c4d43; font-weight: bold; padding: 6px 12px; background-color: #f5efe6; border-radius: 4px; font-size: 0.9rem; box-shadow: 0 1px 3px rgba(0,0,0,0.05);">
            翌月 ▶
        </a>
        
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
                        <td class="${day.currentMonth ? '' : 'other-month'}">
                            
                            <!-- 日付の数字 -->
                            <div class="day-number">${day.dayOfMonth}</div>
                            
                            <!-- その日のシフト情報（複数人いる場合は縦に並ぶ） -->
                            <div class="shift-info-area">
                                <c:forEach var="shift" items="${day.shifts}">
                                    
                                    <c:choose>
                                        <c:when test="${sessionScope.user.role == 'ADMIN'}">
                                        
                                            <a href="${pageContext.request.contextPath}/ShiftEditServlet?shift_id=${shift.shiftId}" class="shift-edit-link" style="text-decoration: none; display: block; color: inherit;">
                                                <div class="shift-badge" style="border: 1px dashed #ffc107; cursor: pointer;">
                                                    <span class="staff-name">${shift.staffName}</span>
                                                    <span class="shift-time">${shift.startTime}〜${shift.endTime}</span>
                                                </div>
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- 一般ユーザーはリンクなし -->
                                            <div class="shift-badge">
                                                <span class="staff-name">${shift.staffName}</span>
                                                <span class="shift-time">${shift.startTime}〜${shift.endTime}</span>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                    
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
