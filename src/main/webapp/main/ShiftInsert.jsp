<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 管理者ではない場合はメニューへ --%>
<c:if test="${sessionScope.user.role != 'ADMIN'}">
    <c:redirect url="/main/main.jsp" />
</c:if>

<%@include file="../header.jsp"%>

<div class="shift-insert-container" style="max-width: 500px; margin: 30px auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #fff; font-family: sans-serif;">
    
    <h2 style="border-bottom: 2px solid #007bff; padding-bottom: 10px; color: #333;">新規シフトの登録</h2>
    
    <form action="${pageContext.request.contextPath}/ShiftInsertServlet" method="post" style="margin-top: 20px;">
        
        <div style="margin-bottom: 15px;">
            <label for="user_id" style="display: block; font-weight: bold; margin-bottom: 5px;">スタッフ名:</label>
            <select name="user_id" id="user_id" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
                <option value="">-- スタッフを選択してください --</option>
                <c:forEach var="staff" items="${staffList}">
                    <option value="${staff.user_id}">${staff.user_name}</option>
                </c:forEach>
            </select>
        </div>
        
        <div style="margin-bottom: 15px;">
            <label for="work_date" style="display: block; font-weight: bold; margin-bottom: 5px;">勤務日:</label>
            <input type="date" name="work_date" id="work_date" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;">
        </div>
        
        <div style="margin-bottom: 15px;">
            <label for="start_time" style="display: block; font-weight: bold; margin-bottom: 5px;">開始時間:</label>
            <input type="time" name="start_time" id="start_time" required step="60" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;">
        </div>
        
        <div style="margin-bottom: 20px;">
            <label for="end_time" style="display: block; font-weight: bold; margin-bottom: 5px;">終了時間:</label>
            <input type="time" name="end_time" id="end_time" required step="60" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;">
        </div>
        
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <a href="${pageContext.request.contextPath}/ShiftListServlet" style="color: #666; text-decoration: none;">
                ⬅️ 一覧に戻る
            </a>
            <button type="submit" style="background-color: #28a745; color: white; border: none; padding: 10px 20px; border-radius: 4px; font-weight: bold; cursor: pointer;">
                この内容で登録する
            </button>
        </div>
        
    </form>
</div>

<%@include file="../footer.jsp"%>
