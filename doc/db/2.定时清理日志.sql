set GLOBAL event_scheduler = ON;
show variables like 'event_scheduler';
delimiter $$
DROP event IF EXISTS xxl_job_log_clean;
create event xxl_job_log_clean
on schedule every 1 hour
do
begin
    start transaction;
    set @timenow=now(); #开始事务

		truncate table xxl_job_log;

    commit;  #提交事务
end  $$

delimiter ;

select * from mysql.event;