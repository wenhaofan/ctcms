#sql("page")
	select * from video 
	#if(query!=null)
		where
		1=1
		#if(query.start!=null)
			and 
				unix_timestamp(createDate) >= #(query.start.getTime()/1000)
		#end
		#if(query.end!=null)
			and 
				unix_timestamp(createDate) <= #(query.end.getTime()/1000)
		#end
	#end
	
	order by createDate desc
#end
