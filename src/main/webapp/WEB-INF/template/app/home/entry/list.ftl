<!DOCTYPE html>
<html>
<head>
	<#include "/common/layout/header.ftl" > 
	<script>
		function toggleFieldset(el) {
			var fieldset = $(el).parents('fieldset').first();
			fieldset.toggleClass('collapsed');
			fieldset.children('div').toggle();
		}

		$(function(){
			$("tr[id^=entry-]").click(function(){
				if ($(this).hasClass("context-menu-selection")) {
					$(this).removeClass("context-menu-selection");
				} else {
					$(this).addClass("context-menu-selection");
				}
			});
		});
	</script>
</head>
<body class="theme-Redmine project-redmine controller-issues action-index">
	<div id="wrapper">
		<div id="wrapper2">
			<div id="wrapper3">
			
				<#include "/common/layout/menu.ftl" > 
				
				<!-- Main -->
				<div id="main">
					<div id="sidebar">
						<a name="Latest-releases"></a>
						<h3>Latest Project<a href="#Latest-releases" class="wiki-anchor">¶</a></h3>
						<p>
							<a href="/projects/redmine/wiki/Download" class="wiki-page">NetAct 15.2</a><br>
							<a href="/projects/redmine/wiki/Download" class="wiki-page">NetAct 15</a><br>
							<a href="/projects/redmine/wiki/Download" class="wiki-page">NetAct 8 EFP1</a><br>
							<a href="/projects/redmine/wiki/Download" class="wiki-page">NetAct 8 EP1</a><br>
							<a href="/projects/redmine/wiki/Download" class="wiki-page">NetAct 8</a>
						</p>
					</div>
					 
					 <div id="content">
						
						<div id="query_form_content">
						<fieldset id="filters" class="collapsible ">
							  <legend onclick="toggleFieldset(this);">Filters</legend>
							  <div style="">
								<script type="text/javascript">
									//<![CDATA[

									var operatorLabels = {"=":"\u7b49\u4e8e","!":"\u4e0d\u7b49\u4e8e","o":"\u6253\u5f00","c":"\u5df2\u5173\u95ed","!*":"\u65e0","*":"\u5168\u90e8",">=":">=","<=":"<=","><":"\u4ecb\u4e8e","<t+":"\u5269\u4f59\u5929\u6570\u5c0f\u4e8e",">t+":"\u5269\u4f59\u5929\u6570\u5927\u4e8e","><t+":"\u5728\u672a\u6765\u51e0\u5929\u4e4b\u5185","t+":"\u5269\u4f59\u5929\u6570","t":"\u4eca\u5929","ld":"\u6628\u5929","w":"\u672c\u5468","lw":"\u4e0a\u5468","l2w":"\u4e0a 2 \u5468\u524d","m":"\u672c\u6708","lm":"\u4e0a\u6708","y":"\u4eca\u5e74",">t-":"\u4e4b\u524d\u5929\u6570\u5c11\u4e8e","<t-":"\u4e4b\u524d\u5929\u6570\u5927\u4e8e","><t-":"\u5728\u8fc7\u53bb\u51e0\u5929\u4e4b\u5185","t-":"\u4e4b\u524d\u5929\u6570","~":"\u5305\u542b","!~":"\u4e0d\u5305\u542b","=p":"\u9879\u76ee\u5185\u4efb\u610f\u95ee\u9898","=!p":"\u9879\u76ee\u5916\u4efb\u610f\u95ee\u9898","!p":"\u9879\u76ee\u5185\u65e0\u76f8\u5173\u95ee\u9898"};
									var operatorByType = {"list":["=","!"],"list_status":["o","=","!","c","*"],"list_optional":["=","!","!*","*"],"list_subprojects":["*","!*","="],"date":["=",">=","<=","><","<t+",">t+","><t+","t+","t","ld","w","lw","l2w","m","lm","y",">t-","<t-","><t-","t-","!*","*"],"date_past":["=",">=","<=","><",">t-","<t-","><t-","t-","t","ld","w","lw","l2w","m","lm","y","!*","*"],"string":["=","~","!","!~","!*","*"],"text":["~","!~","!*","*"],"integer":["=",">=","<=","><","!*","*"],"float":["=",">=","<=","><","!*","*"],"relation":["=","=p","=!p","!p","!*","*"]};
									var availableFilters = {"status_id":{"type":"list_status","name":"\u72b6\u6001","values":[["New","1"],["Needs feedback","10"],["Confirmed","9"],["Resolved","3"],["Closed","5"],["Reopened","8"]]},"tracker_id":{"type":"list","name":"\u8ddf\u8e2a","values":[["Defect","1"],["Feature","2"],["Patch","3"]]},"priority_id":{"type":"list","name":"\u4f18\u5148\u7ea7","values":[["Low","3"],["Normal","4"],["High","5"],["Urgent","6"]]},"author_id":{"type":"list","name":"\u4f5c\u8005","values":[["Azamat Hackimov","1592"],["Chaoqun Zou","460"],["Daniel Felix","61731"],["Etienne Massip","6508"],["Filou Centrinov","69004"],["Jan from Planio www.plan.io","347"],["Jan Niggemann (redmine.org team member)","55460"],["Jean-Baptiste Barth","1188"],["Jean-Philippe Lang","1"],["Jim Mulholland","551"],["Jonas De Meulenaere","1287"],["Karl Heinz Marbaise","2203"],["Maxim Kru\u0161ina","574"],["Mischa The Evil","1565"],["Toshi MARUYAMA","11192"]]},"assigned_to_id":{"type":"list_optional","name":"\u6307\u6d3e\u7ed9","values":[["Azamat Hackimov","1592"],["Chaoqun Zou","460"],["Daniel Felix","61731"],["Etienne Massip","6508"],["Filou Centrinov","69004"],["Jan from Planio www.plan.io","347"],["Jan Niggemann (redmine.org team member)","55460"],["Jean-Baptiste Barth","1188"],["Jean-Philippe Lang","1"],["Jim Mulholland","551"],["Jonas De Meulenaere","1287"],["Karl Heinz Marbaise","2203"],["Maxim Kru\u0161ina","574"],["Mischa The Evil","1565"],["Toshi MARUYAMA","11192"]]},"assigned_to_role":{"type":"list_optional","name":"\u89d2\u8272\u7684\u6210\u5458","values":[["Administrator","3"],["Contributor","4"],["Release manager","5"]]},"fixed_version_id":{"type":"list_optional","name":"\u76ee\u6807\u7248\u672c","values":[["Redmine - 0.7","1"],["Redmine - 0.7.1","3"],["Redmine - 0.7.2","4"],["Redmine - 0.7.3","5"],["Redmine - 0.8","2"],["Redmine - 0.8.1","7"],["Redmine - 0.8.2","8"],["Redmine - 0.8.3","9"],["Redmine - 0.8.4","10"],["Redmine - 0.8.5","11"],["Redmine - 0.8.6","12"],["Redmine - 0.8.7","13"],["Redmine - 0.9.0","6"],["Redmine - 0.9.1","15"],["Redmine - 0.9.2","16"],["Redmine - 0.9.3","17"],["Redmine - 0.9.4","18"],["Redmine - 0.9.5","19"],["Redmine - 0.9.6","22"],["Redmine - 1.0.0 (RC)","14"],["Redmine - 1.0.1","21"],["Redmine - 1.0.2","24"],["Redmine - 1.0.3","25"],["Redmine - 1.0.4","26"],["Redmine - 1.0.5","28"],["Redmine - 1.1.0","20"],["Redmine - 1.1.1","30"],["Redmine - 1.1.2","31"],["Redmine - 1.1.3","34"],["Redmine - 1.2.0","27"],["Redmine - 1.2.1","35"],["Redmine - 1.2.2","38"],["Redmine - 1.2.3","39"],["Redmine - 1.3.0","36"],["Redmine - 1.3.1","41"],["Redmine - 1.3.2","42"],["Redmine - 1.3.3","44"],["Redmine - 1.4.0","40"],["Redmine - 1.4.1","46"],["Redmine - 1.4.2","48"],["Redmine - 2.0.0","43"],["Redmine - 2.0.1","49"],["Redmine - 1.4.3","51"],["Redmine - 2.0.2","50"],["Redmine - 1.4.4","53"],["Redmine - 2.0.3","52"],["Redmine - 2.0.4","54"],["Redmine - 2.1.0","47"],["Redmine - 2.1.1","57"],["Redmine - 2.1.2","58"],["Redmine - 1.4.5","55"],["Redmine - 2.1.3","59"],["Redmine - 2.1.4","61"],["Redmine - 2.1.5","62"],["Redmine - 2.2.0","56"],["Redmine - 1.4.6","65"],["Redmine - 2.1.6","64"],["Redmine - 2.2.1","63"],["Redmine - 1.4.7","67"],["Redmine - 2.2.2","66"],["Redmine - 2.2.3","68"],["Redmine - 2.2.4","69"],["Redmine - 2.3.0","60"],["Redmine - 2.3.1","71"],["Redmine - 2.3.2","72"],["Redmine - 2.3.3","73"],["Redmine - 2.3.4","74"],["Redmine - 2.4.0","70"],["Redmine - 2.4.1","76"],["Redmine - 2.4.2","77"],["Redmine - 2.4.3","78"],["Redmine - 2.4.4","79"],["Redmine - 2.5.0","75"],["Redmine - 2.4.5","83"],["Redmine - 2.5.1","82"],["Redmine - 2.4.6","85"],["Redmine - 2.4.7","87"],["Redmine - 2.5.2","84"],["Redmine - 2.5.3","86"],["Redmine - 2.6.0","81"],["Redmine - 3.0.0","80"],["Redmine - Candidate for next major release","32"],["Redmine - Candidate for next minor release","33"],["Redmine - Unplanned","23"]]},"category_id":{"type":"list_optional","name":"\u7c7b\u522b","values":[["Accounts \/ authentication","7"],["Activity view","50"],["Administration","8"],["Attachments","19"],["Calendar","36"],["Code cleanup\/refactoring","30"],["Core Plugins","35"],["Custom fields","14"],["Database","21"],["Documentation","24"],["Documents","4"],["Email notifications","9"],["Email receiving","29"],["Feeds","18"],["Files","52"],["Forums","5"],["Gantt","34"],["Gems support","45"],["Groups","27"],["Hook requests","54"],["I18n","37"],["Importers","15"],["Issues","2"],["Issues permissions","23"],["Issues planning","40"],["Issues workflow","41"],["LDAP","28"],["My page","42"],["News","6"],["OpenID","49"],["PDF export","39"],["Performance","53"],["Permissions and roles","17"],["Plugin API","20"],["Plugin Request","31"],["Project settings","43"],["Projects","11"],["Rails support","44"],["REST API","32"],["Roadmap","22"],["Ruby support","33"],["SCM","3"],["SCM extra","46"],["Search engine","16"],["Security","51"],["SEO","48"],["Text formatting","26"],["Themes","38"],["Third-party libraries","47"],["Time tracking","13"],["Translations","12"],["UI","10"],["Website (redmine.org)","25"],["Wiki","1"]]},"subject":{"type":"text","name":"\u4e3b\u9898"},"created_on":{"type":"date_past","name":"\u521b\u5efa\u4e8e"},"updated_on":{"type":"date_past","name":"\u66f4\u65b0\u4e8e"},"closed_on":{"type":"date_past","name":"\u7ed3\u675f\u65e5\u671f"},"start_date":{"type":"date","name":"\u5f00\u59cb\u65e5\u671f"},"due_date":{"type":"date","name":"\u8ba1\u5212\u5b8c\u6210\u65e5\u671f"},"estimated_hours":{"type":"float","name":"\u9884\u671f\u65f6\u95f4"},"done_ratio":{"type":"integer","name":"% \u5b8c\u6210"},"cf_2":{"type":"list_optional","name":"Resolution","values":["Fixed","Wont fix","Invalid","Duplicate","Cant reproduce","No feedback"]},"cf_4":{"type":"list_optional","name":"Affected version","values":[["0.7","1"],["0.7.1","3"],["0.7.2","4"],["0.7.3","5"],["0.8","2"],["0.8.1","7"],["0.8.2","8"],["0.8.3","9"],["0.8.4","10"],["0.8.5","11"],["0.8.6","12"],["0.8.7","13"],["0.9.0","6"],["0.9.1","15"],["0.9.2","16"],["0.9.3","17"],["0.9.4","18"],["0.9.5","19"],["0.9.6","22"],["1.0.0 (RC)","14"],["1.0.1","21"],["1.0.2","24"],["1.0.3","25"],["1.0.4","26"],["1.0.5","28"],["1.1.0","20"],["1.1.1","30"],["1.1.2","31"],["1.1.3","34"],["1.2.0","27"],["1.2.1","35"],["1.2.2","38"],["1.2.3","39"],["1.3.0","36"],["1.3.1","41"],["1.3.2","42"],["1.3.3","44"],["1.4.0","40"],["1.4.1","46"],["1.4.2","48"],["2.0.0","43"],["2.0.1","49"],["1.4.3","51"],["2.0.2","50"],["1.4.4","53"],["2.0.3","52"],["2.0.4","54"],["2.1.0","47"],["2.1.1","57"],["2.1.2","58"],["1.4.5","55"],["2.1.3","59"],["2.1.4","61"],["2.1.5","62"],["2.2.0","56"],["1.4.6","65"],["2.1.6","64"],["2.2.1","63"],["1.4.7","67"],["2.2.2","66"],["2.2.3","68"],["2.2.4","69"],["2.3.0","60"],["2.3.1","71"],["2.3.2","72"],["2.3.3","73"],["2.3.4","74"],["2.4.0","70"],["2.4.1","76"],["2.4.2","77"],["2.4.3","78"],["2.4.4","79"],["2.5.0","75"],["2.4.5","83"],["2.5.1","82"],["2.4.6","85"],["2.5.2","84"]]},"relates":{"type":"relation","name":"\u5173\u8054\u5230"},"duplicates":{"type":"relation","name":"\u91cd\u590d"},"duplicated":{"type":"relation","name":"\u4e0e\u5176\u91cd\u590d"},"blocks":{"type":"relation","name":"\u963b\u6321"},"blocked":{"type":"relation","name":"\u88ab\u963b\u6321"},"precedes":{"type":"relation","name":"\u4f18\u5148\u4e8e"},"follows":{"type":"relation","name":"\u8ddf\u968f\u4e8e"},"copied_to":{"type":"relation","name":"\u590d\u5236\u5230"},"copied_from":{"type":"relation","name":"\u590d\u5236\u4e8e"}};
									var labelDayPlural = "\u5929";
									var allProjects = [["Redmine","1"]];
									$(document).ready(function(){
									  initFilters();
									  addFilter("status_id", "o", [""]);
									});

									//]]>
								</script>
								<table style="width:100%">
									<tbody><tr>
										<td>
											<table id="filters-table">
												<tbody>
													<tr class="filter" id="tr_status_id">
														<td class="field">
															<input checked="checked" id="cb_status_id" name="f[]" value="status_id" type="checkbox">
															<label for="cb_status_id"> 状态</label>
														</td>
														<td class="operator">
															<select id="operators_status_id" name="op[status_id]">
																<option value="o" selected="selected">打开</option>
																<option value="=">等于</option><option value="!">不等于</option>
																<option value="c">已关闭</option><option value="*">全部</option>
															</select>
														</td>
														<td class="values">
															<span style="display: none;">
																<select class="value" id="values_status_id_1" name="v[status_id][]" disabled="disabled">
																	<option value="1">New</option>
																	<option value="10">Needs feedback</option>
																	<option value="9">Confirmed</option>
																	<option value="3">Resolved</option>
																	<option value="5">Closed</option>
																	<option value="8">Reopened</option>
																</select> 
																<span class="toggle-multiselect">&nbsp;</span>
															</span>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
										<td class="add-filter">
											<label for="add_filter_select">增加过滤器</label>
											<select id="add_filter_select">
												<option value="">&nbsp;</option>
												<option value="status_id" disabled="disabled">状态</option>
												<option value="tracker_id">跟踪</option>
												<option value="priority_id">优先级</option>
												<option value="author_id">作者</option>
												<option value="assigned_to_id">指派给</option>
												<option value="assigned_to_role">角色的成员</option>
												<option value="fixed_version_id">目标版本</option>
												<option value="category_id">类别</option>
												<option value="subject">主题</option>
												<option value="created_on">创建于</option>
												<option value="updated_on">更新于</option>
												<option value="closed_on">结束日期</option>
												<option value="start_date">开始日期</option>
												<option value="due_date">计划完成日期</option>
												<option value="estimated_hours">预期时间</option>
												<option value="done_ratio">% 完成</option>
												<option value="cf_2">Resolution</option>
												<option value="cf_4">Affected version</option>
												<option value="relates">关联到</option>
												<option value="duplicates">重复</option>
												<option value="duplicated">与其重复</option>
												<option value="blocks">阻挡</option>
												<option value="blocked">被阻挡</option>
												<option value="precedes">优先于</option>
												<option value="follows">跟随于</option>
												<option value="copied_to">复制到</option>
												<option value="copied_from">复制于</option>
											</select>
										</td>
									</tr>
									</tbody>
								</table>
								<input id="f_" name="f[]" type="hidden" value="">
							  </div>
							</fieldset>
							
							<fieldset class="collapsible collapsed">
								<legend onclick="toggleFieldset(this);">Columns</legend>
								  <div style="display: none;">
									<table>
									  <tbody>
										<tr>
											<td>列</td>
											<td>
												<table class="query-columns">
													<tbody>
														<tr>
															<td style="padding-left:0">
																<label for="available_columns">备选列</label><br>
																	<select id="available_columns" multiple="multiple" name="available_columns[]" ondblclick="moveOptions(this.form.available_columns, this.form.selected_columns);" size="10" style="width:150px">
																		<option value="project">项目</option>
																		<option value="parent">父任务</option>
																		<option value="priority">优先级</option>
																		<option value="author">作者</option>
																		<option value="assigned_to">指派给</option>
																		<option value="fixed_version">目标版本</option>
																		<option value="start_date">开始日期</option>
																		<option value="due_date">计划完成日期</option>
																		<option value="estimated_hours">预期时间</option>
																		<option value="done_ratio">% 完成</option>
																		<option value="created_on">创建于</option>
																		<option value="closed_on">结束日期</option>
																		<option value="relations">相关的问题</option>
																		<option value="cf_2">Resolution</option>
																		<option value="cf_4">Affected version</option>
																	</select>
															</td>
															<td class="buttons">
																<input type="button" value="→" onclick="moveOptions(this.form.available_columns, this.form.selected_columns);"><br>
																<input type="button" value="←" onclick="moveOptions(this.form.selected_columns, this.form.available_columns);">
															</td>
															<td>
																<label for="selected_columns">已选列</label><br>
																	<select id="selected_columns" multiple="multiple" name="c[]" ondblclick="moveOptions(this.form.selected_columns, this.form.available_columns);" size="10" style="width:150px">
																		<option value="tracker">跟踪</option>
																		<option value="status">状态</option>
																		<option value="subject">主题</option>
																		<option value="updated_on">更新于</option>
																		<option value="category">类别</option>
																	</select>
															</td>
															<td class="buttons">
															  <input type="button" value="⇈" onclick="moveOptionTop(this.form.selected_columns);"><br>
															  <input type="button" value="↑" onclick="moveOptionUp(this.form.selected_columns);"><br>
															  <input type="button" value="↓" onclick="moveOptionDown(this.form.selected_columns);"><br>
															  <input type="button" value="⇊" onclick="moveOptionBottom(this.form.selected_columns);">
															</td>
														</tr>
													</tbody>
												</table>
											</td>	
										</tr>
										<tr>
											<td><label for="group_by">根据此条件分组</label></td>
											<td>
												<select id="group_by" name="group_by">
													<option value="">&nbsp;</option>
													<option value="project">项目</option>
													<option value="tracker">跟踪</option>
													<option value="status">状态</option>
													<option value="priority">优先级</option>
													<option value="author">作者</option>
													<option value="assigned_to">指派给</option>
													<option value="category">类别</option>
													<option value="fixed_version">目标版本</option>
													<option value="done_ratio">% 完成</option>
													<option value="cf_2">Resolution</option>
													<option value="cf_4">Affected version</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>显示</td>
											<td><label class="inline"><input name="c[]" type="checkbox" value="description"> 描述</label></td>
										</tr>
									</tbody>
								</table>
							  </div>
							</fieldset>
						</div>
						
						<div class="autoscroll">
							<table class="list issues">
								<thead>
									<tr>
										<th title="根据 &quot;#&quot; 排序"><a href="/projects/redmine/issues?per_page=100&amp;sort=id" class="sort desc">#</a></th>
										<th title="根据 &quot;状态&quot; 排序"><a href="/projects/redmine/issues?per_page=100&amp;sort=status%2Cid%3Adesc">Source System</a></th>
										<th title="根据 &quot;跟踪&quot; 排序"><a href="/projects/redmine/issues?per_page=100&amp;sort=tracker%2Cid%3Adesc">Source Port</a></th>
										<th title="根据 &quot;状态&quot; 排序"><a href="/projects/redmine/issues?per_page=100&amp;sort=status%2Cid%3Adesc">Dest. System</a></th>
										<th title="根据 &quot;跟踪&quot; 排序"><a href="/projects/redmine/issues?per_page=100&amp;sort=tracker%2Cid%3Adesc">Dest. Port</a></th>
										<th title="根据 &quot;主题&quot; 排序"><a href="/projects/redmine/issues?per_page=100&amp;sort=subject%2Cid%3Adesc">Owner</a></th>
										<th title="根据 &quot;更新于&quot; 排序"><a href="/projects/redmine/issues?per_page=100&amp;sort=updated_on%3Adesc%2Cid%3Adesc">Update Time</a></th>
										<th title="根据 &quot;类别&quot; 排序"><a href="/projects/redmine/issues?per_page=100&amp;sort=category%2Cid%3Adesc">Project</a></th>
									</tr>
								 </thead>
								 <tbody>
									<tr id="entry-17590" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-08-01 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									 </tr>
									<tr id="entry-17588" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 10:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
									<tr id="entry-17540" class="entry odd">
										<td><a href="/issues/17590">17590</a></td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td>iNUM OAM</td>
										<td>8080</td>
										<td><a href="/issues/17590">rachel</a></td>
										<td>2014-07-29 09:22</td>
										<td><a href="/issues/17590">NetAct 8 EP2</a></td>
									 </tr>
									<tr id="entry-17538" class="entry even">
										<td><a href="/issues/17590">17590</a></td>
										<td>One-EIR</td>
										<td>8080</td>
										<td>LB_JBI</td>
										<td>30505</td>
										<td><a href="/issues/17590">x36zhao</a></td>
										<td>2014-07-28 05:22</td>
										<td><a href="/issues/17590">NetAct 8 EP1</a></td>
									</tr>
								 </tbody>
							</table>
						</div>
					 
						<p class="pagination">
							<span class="current page">1</span> 
							<a href="/projects/redmine/issues?page=2&amp;per_page=25" class="page">2</a> 
							<a href="/projects/redmine/issues?page=3&amp;per_page=25" class="page">3</a> 
							<span class="spacer">...</span> <a href="/projects/redmine/issues?page=175&amp;per_page=25" class="page">175</a> 
							<a href="/projects/redmine/issues?page=2&amp;per_page=25" class="next">下一页 »</a> 
							<span class="items">(1-25/4364)</span> <span class="per-page">每页显示：<span>25</span>, 
							<a href="/projects/redmine/issues?per_page=50">50</a>, <a href="/projects/redmine/issues?per_page=100">100</a></span>
						</p>
					</div>
					 
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>