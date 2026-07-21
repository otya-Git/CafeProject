<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:if test="${sessionScope.user.role != 'ADMIN'}">
    <c:redirect url="/main/main.jsp" />
</c:if>

<%@include file="../header.jsp"%>

<div class="shift-edit-container" style="max-width: 500px; margin: 30px auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #fff; font-family: sans-serif;">
    
    <h2 style="border-bottom: 2px solid #ffc107; padding-bottom: 10px; color: #333;">シフト情報の編集</h2>
    
    <form action="${pageContext.request.contextPath}/ShiftEditServlet" method="post" style="margin-top: 20px;">
        
        <%
            bean.Shift s = (bean.Shift)request.getAttribute("Shift");
            if (s == null) {
                s = (bean.Shift)request.getAttribute("shift");
            }
        %>
        
        <input type="hidden" name="shift_id" value="<%= s.getshift_id() %>">
        
        <div style="margin-bottom: 15px;">
            <label for="user_id" style="display: block; font-weight: bold; margin-bottom: 5px;">スタッフ名:</label>
            <select name="user_id" id="user_id" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
                <c:forEach var="staff" items="${staffList}">
                    <option value="${staff.user_id}" ${staff.user_id == shift.user_id || staff.user_id == Shift.user_id ? 'selected' : ''}>
                        ${staff.user_name}
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <div style="margin-bottom: 15px;">
            <label for="work_date" style="display: block; font-weight: bold; margin-bottom: 5px;">勤務日:</label>
            <input type="date" name="work_date" id="work_date" value="<%= s.getWork_date() %>" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;">
        </div>
        
        <div style="margin-bottom: 15px;">
            <label for="start_time" style="display: block; font-weight: bold; margin-bottom: 5px;">開始時間:</label>
            <input type="time" name="start_time" id="start_time" value="<%= s.getstart_time() %>" required step="60" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;">
        </div>
        
        <div style="margin-bottom: 20px;">
            <label for="end_time" style="display: block; font-weight: bold; margin-bottom: 5px;">終了時間:</label>
            <input type="time" name="end_time" id="end_time" value="<%= s.getend_time() %>" required step="60" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;">
        </div>
        
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <a href="${pageContext.request.contextPath}/ShiftServlet" style="color: #666; text-decoration: none;">
                ⬅️ キャンセルして戻る
            </a>
            <button type="submit" style="background-color: #ffc107; color: #333; border: none; padding: 10px 20px; border-radius: 4px; font-weight: bold; cursor: pointer;">
                変更を保存する
            </button>
        </div>
        
    </form>
</div>

<%@include file="../footer.jsp"%>
