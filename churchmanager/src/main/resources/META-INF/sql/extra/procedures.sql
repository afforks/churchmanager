DELIMITER $$
 CREATE PROCEDURE gerar_parcela_movimentacao()
 	
    BEGIN
		DECLARE CNT INT DEFAULT 1;
		DECLARE CAT INT DEFAULT 1;
		DECLARE QTD_MOVIMENTACAO INT DEFAULT (SELECT COUNT(id) from movimentacao);
    
		WHILE CNT <= QTD_MOVIMENTACAO DO
        
			update movimentacao set categoria_id = CAT WHERE id = CNT;
		
			INSERT INTO parcela_movimentacao 
				(status, versao, data_vencimento, descricao, status_movimentacao, valor_parcela, movimentacao_id, nome, data_pagamento)	
			SELECT m.status, m.versao, m.data_vencimento, m.descricao, m.status_movimentacao, m.valor, m.id, 'Parcela única', (CASE WHEN status_movimentacao = 'PAGO' THEN m.data_vencimento ELSE null END) from movimentacao m WHERE m.id = CNT; 
            
			IF CAT =  10 THEN
				SET CAT = 0;
			END IF;
         
			SET CNT = CNT + 1;
			SET CAT = CAT + 1;
		
	END WHILE;
END;