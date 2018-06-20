<!-- 商品分类banner -->
	    <div id="menu">
            <div class="w1190">
                <div class="menuleft">
                    <div class="spfl" id="dropmenubtn"><a>全部商品分类</a></div>
                    <div id="dropmenu" class="menu-box">
						<#list list as item>
							<#if item_index gt 6>
                                <#break >
                            </#if>
                            <dl class="fist text">
                                <dt>
                                    <img
											<#if item.iconFile??>
                                                <#assign iconFile=item.iconFile />
											   src="/file/${iconFile.http_relative_path!}"
                                            </#if>
                                    />
                                    <a title="${item.description!}" href="/public/classify/1/1/1/0/0/${item.id}.html">${item.name}</a>
                                    <img class="jiantou" src="/img/jt.png">
                                </dt>
                                <dd>
                                    <ul>
										<#list item.commoditySecondClassifyList as scItem>
                                            <li>
                                                <strong><a title="${scItem.description}" href="/public/classify/2/1/1/0/0/${scItem.id}.html">${scItem.name}</a></strong>
                                                <p>
													<#list scItem.commodityThirdClassifyList as tdItem>
                                                        <a title="${tdItem.name}" href="/public/classify/3/1/1/0/0/${scItem.id}.html">${tdItem.name}</a>
														<#if tdItem_has_next>
															<span>|</span>
                                                        </#if>
                                                    </#list>
                                                </p>
                                            </li>
                                        </#list>
                                    </ul>

                                </dd>
                            </dl>
                        </#list>
                    </div>
                </div>
                <!-- 优惠卷 -->
                <a href="#" class="yhj"></a>
            </div>
        </div>