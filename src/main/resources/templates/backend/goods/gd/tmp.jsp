<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <input type="hidden" name="attr_id" class="attr_id"  value="${item.id }"/>
												
                                            <td style="text-align:center;">${item.name }</td>
                                            <td style="text-align:center;">
                                            
                                            	<div class="">
									
										<div class="col-lg-9">
											 <input  type="text"  required data-bv-notempty-message="${item.name}不能为空"   name="attr_val_${item?index} }" class="attr_type form-control" 
												placeholder="${item.name }">
											<p class="help-block"></p>
										</div>
									</div>
                                            
                                         
											
                                            </td>
                                            <td style="text-align:center;">
                                            	<button type="button " class="hide btn btn-danger btn-warning " data-dismiss="modal ">删除
									</button>
											
                                            </td> 
</body>
</html>