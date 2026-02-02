-- ========================================
-- 商品管理システム データベース初期化スクリプト
-- ========================================

-- データベースの作成
CREATE DATABASE IF NOT EXISTS mall_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE mall_db;

-- ========================================
-- ユーザーテーブル
-- ========================================
CREATE TABLE IF NOT EXISTS `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ユーザーID',
    `username` VARCHAR(50) NOT NULL COMMENT 'ユーザー名',
    `password` VARCHAR(255) NOT NULL COMMENT 'パスワード（暗号化保存）',
    `role` VARCHAR(20) NOT NULL DEFAULT 'ADMIN' COMMENT 'ロール（ADMIN-管理者）',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'ステータス（0-無効、1-有効）',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ユーザーテーブル';

-- ========================================
-- 商品テーブル
-- ========================================
CREATE TABLE IF NOT EXISTS `product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `product_name` VARCHAR(200) NOT NULL COMMENT '商品名',
    `product_code` VARCHAR(50) NOT NULL COMMENT '商品コード',
    `category` VARCHAR(100) COMMENT '商品カテゴリ',
    `price` DECIMAL(10, 2) NOT NULL COMMENT '価格',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '在庫数',
    `description` TEXT COMMENT '商品説明',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT 'ステータス（0-購入不可、1-購入可能）',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_product_code` (`product_code`),
    KEY `idx_category` (`category`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品テーブル';

-- ========================================
-- 初期管理者アカウントの挿入（パスワード：123123）
-- ========================================
INSERT INTO `users` (`username`, `password`, `role`, `status`) 
VALUES ('zhl', '123123', 'ADMIN', 1)
ON DUPLICATE KEY UPDATE `username` = `username`;

-- ========================================
-- テスト用商品データの挿入
-- ========================================
INSERT INTO `product` (`product_name`, `product_code`, `category`, `price`, `stock`, `description`, `status`) 
VALUES 
    ('iPhone 15 Pro', 'PHONE-001', 'スマートフォン', 7999.00, 100, 'Apple iPhone 15 Pro 256GB スペースブラック', 1),
    ('MacBook Pro', 'LAPTOP-001', 'ノートパソコン', 12999.00, 50, 'Apple MacBook Pro 14インチ M3チップ', 1),
    ('AirPods Pro', 'AUDIO-001', 'イヤホン', 1999.00, 200, 'Apple AirPods Pro 第2世代 アクティブノイズキャンセリング', 1),
    ('iPad Air', 'TABLET-001', 'タブレット', 4799.00, 80, 'Apple iPad Air 11インチ M2チップ', 1),
    ('Apple Watch', 'WATCH-001', 'スマートウォッチ', 3199.00, 150, 'Apple Watch Series 9 GPS 45mm', 1)
ON DUPLICATE KEY UPDATE `product_code` = `product_code`;