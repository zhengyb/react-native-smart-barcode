
#import <UIKit/UIKit.h>

@interface RectView : UIView

@property (nonatomic,assign)CGRect scannerRect;
@property (nonatomic, copy) NSString *scannerRectCornerColor;
@property (nonatomic, copy) NSString *scannerText;
@property (nonatomic,strong)UILabel *noticeInfoLable;

- (id)initWithScannerRect:(CGRect)scannerRect frame:(CGRect)frame scannerRectCornerColor:(NSString*)scannerRectCornerColor scannerText:(NSString*)scannerText;

@end
